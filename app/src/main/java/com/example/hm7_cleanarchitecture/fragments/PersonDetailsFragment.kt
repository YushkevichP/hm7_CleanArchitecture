package com.example.hm7_cleanarchitecture.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.hm7_cleanarchitecture.databinding.FragmentPersonDetailsBinding
import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.utilities.networkChangeFlow
import com.example.hm7_cleanarchitecture.viewmodels.PersonDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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

        requireContext().networkChangeFlow
            .onEach {
                when (it) {
                    true -> Log.d("check", "Есть конекшн")
                    false -> Snackbar.make(view, "Нет сети", Snackbar.LENGTH_LONG).show()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.toolbar.setupWithNavController(findNavController()) // back_arrow

        // с лце
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getdataFlow()
                .onEach {
                    when (it) {
                        is LceState.Content -> {
                            with(binding) {

                                imageUserFragment.load(it.data.avatarApiDetails)
                                personGender.text = it.data.gender
                                personName.text = it.data.name
                                personStatus.text = it.data.status
                            }
                        }
                        is LceState.Error -> {
                            //todo можно показать снекбар
                            Toast.makeText(requireContext(), "$it + OOOOPS", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is LceState.Loading -> {

                        }
                    }
                }.launchIn(viewLifecycleOwner.lifecycleScope)
        }

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


// без лце
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.getdataFlow()
//                .onEach {
//                    with(binding) {
//                        imageUserFragment.load(it.avatarApiDetails)
//                        personGender.text = it.gender
//                        personName.text = it.name
//                        personStatus.text = it.status
//                    }
//                }.launchIn(viewLifecycleOwner.lifecycleScope)
//        }
