package com.example.recipesapp.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.presentation.explore.ExploreViewModel
import com.example.recipesapp.databinding.FragmentExploreBinding

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}