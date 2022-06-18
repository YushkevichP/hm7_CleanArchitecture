package com.example.hm7_cleanarchitecture.fragments

import android.annotation.SuppressLint
import android.content.ClipData
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
import com.example.hm7_cleanarchitecture.domain.model.ItemType
import com.example.hm7_cleanarchitecture.domain.model.LceState
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
                ListFragmentDirections.toDetails(person.id)
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
            .onEach { uiState ->
                val pageList = if (uiState.hasMoreData && uiState.persons.isNotEmpty()) {
                    uiState.persons.map {
                        ItemType.Content(it)
                    } + ItemType.Loading
                } else uiState.persons.map {
                    ItemType.Content(it)
                }

                personAdapter.submitList(pageList)
                binding.progressCircular.isVisible = uiState.persons.isEmpty()
                binding.swipeLayout.isRefreshing = false

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
