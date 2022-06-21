package com.example.hm7_cleanarchitecture.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hm7_cleanarchitecture.databinding.FragmentFavouritesListBinding

class FavouriteFragment: Fragment() {

    private var _binding: FragmentFavouritesListBinding? = null
    private val binding: FragmentFavouritesListBinding
        get() = requireNotNull(_binding) {
            "OOPS"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentFavouritesListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}