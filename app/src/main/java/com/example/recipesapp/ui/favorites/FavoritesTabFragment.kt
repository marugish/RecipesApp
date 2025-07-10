package com.example.recipesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.databinding.FragmentFavoritesTabBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesTabFragment : BaseFragment<FragmentFavoritesTabBinding>() {

    private val viewModel by viewModel<FavoritesTabViewModel>()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoritesTabBinding {
        return FragmentFavoritesTabBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}