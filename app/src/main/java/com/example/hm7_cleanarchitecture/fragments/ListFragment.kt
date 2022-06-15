package com.example.hm7_cleanarchitecture.fragments

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hm7_cleanarchitecture.ItemAdapter
import com.example.hm7_cleanarchitecture.R
import com.example.hm7_cleanarchitecture.databinding.FragmentListBinding
import com.example.hm7_cleanarchitecture.model.ItemType
import com.example.hm7_cleanarchitecture.utilities.networkChangeFlow
import com.example.hm7_cleanarchitecture.viewmodels.ListViewModel
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val viewModel by viewModel<ListViewModel>()

    private val personAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ItemAdapter(requireContext()) { person ->
            findNavController().navigate(
                ListFragmentDirections.toDetails(person.idApi)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewModel.dataFlow
                .onEach { lce ->
                    if (lce.data.isNotEmpty()){
                        //крутелка работает, только когда список пуст (припервой подгрукзке)
                        binding.progressCircular.isVisible = false
                    }
                    Log.d("checkMyApp", "HasMoreData? -->${lce.hasMoreData.toString()}")

                        // проверяем и если посл эл-т то убираем крутелку
                    val pageList = if (lce.hasMoreData && lce.data.isNotEmpty()){
                        lce.data.map {
                            ItemType.Content(it)
                        } + ItemType.Loading
                    } else{
                        lce.data.map {
                            ItemType.Content(it)
                        }
                    }

                    personAdapter.submitList(pageList)
                    binding.swipeLayout.isRefreshing = false

                    if (!lce.throwable?.message.isNullOrBlank()) {
                        Toast.makeText(requireContext(),
                            lce.throwable?.message ?: "",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

        // запихунть куда-то, чтоб не отображалось постаянно / или в тру ничего не делать.
        requireContext().networkChangeFlow
            .onEach {
                when (it) {
                    true -> Toast.makeText(requireContext(), "Працуе канэкшн", Toast.LENGTH_LONG)
                        .show()
                    false -> Toast.makeText(requireContext(),
                        "Не працуе канэкшн",
                        Toast.LENGTH_LONG).show()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)


        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = personAdapter
            recyclerView.addSpaceDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
            swipeLayout.setOnRefreshListener {
                viewModel.onRefresh()
            }

            recyclerView.addPaginationScrollFlow(layoutManager, 6)
                .onEach {
                   viewModel.onLoadMore()
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
