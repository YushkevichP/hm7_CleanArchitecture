package com.example.hm7_cleanarchitecture.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.hm7_cleanarchitecture.databinding.FragmentPersonDetailsBinding
import com.example.hm7_cleanarchitecture.utilities.networkChangeFlow
import com.example.hm7_cleanarchitecture.viewmodels.PersonDetailsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PersonDetailsFragment : Fragment() {

    private var _binding: FragmentPersonDetailsBinding? = null
    private val binding: FragmentPersonDetailsBinding
        get() = requireNotNull(_binding) {
            "VIEW WAS DESTROYED"
        }

    private val args by navArgs<PersonDetailsFragmentArgs>()

    private val viewModel by viewModel<PersonDetailsViewModel> { parametersOf(args.keyId) }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentPersonDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setupWithNavController(findNavController()) // back_arrow

        viewModel.dataFlow
            .onEach {
                it.fold(
                    onSuccess = {
                        with(binding) {
                            imageUserFragment.load(it.avatarApiDetails)
                            personGender.text = it.gender
                            personName.text = it.name
                            personStatus.text = it.status
                        }
                    },
                    onFailure = { Toast.makeText(requireContext(),
                        it.message.toString(),
                        Toast.LENGTH_LONG).show() }
                )
            }.launchIn(viewLifecycleOwner.lifecycleScope)


        requireContext().networkChangeFlow
            .onEach {
                when (it) {
                    true -> Log.d("check", "Есть конекшн")
                    false -> Toast.makeText(requireContext(),
                        "Не працуе канэкшн",
                        Toast.LENGTH_LONG).show()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//    private val viewModel by viewModels<PersonDetailsViewModel> {
//        object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return PersonDetailsViewModel(
//                    persontRepository,
//                ) as T
//            }
//        }
//    }



//    private fun getDetails(id: Int) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            Log.d("LOG", "ВЬЮ МОДЕЛЬ ДОЛЖНА ВКЛ")
//            viewModel.fetchDetails(id)?.onEach {
//                Log.d("LOG", "флоу начинает $it")
//
//                with(binding) {
//                    imageUserFragment.load(it.avatarApiDetails)
//                    personGender.text = it.gender
//                    personName.text = it.name
//                    personStatus.text = it.status
//                }
//            }?.launchIn(viewLifecycleOwner.lifecycleScope)
//        }
//    }




// можно запускать вот так, одно и то же
//    private fun getDetails(id: Int) = with(binding) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.fetchDetails(id)
//                ?.onEach { details ->
//                    checkNotNull(details)
//                    imageUserFragment.load(details.avatarApiDetails)
//                    personGender.text = details.gender
//                    personName.text = details.name
//                    personStatus.text = details.status
//                }?.launchIn(viewLifecycleOwner.lifecycleScope)
//        }
//    }