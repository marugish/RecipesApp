package com.example.recipesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.presentation.favorites.FavoritesViewModel
import com.example.recipesapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}