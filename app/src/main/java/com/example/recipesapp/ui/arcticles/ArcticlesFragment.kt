package com.example.recipesapp.ui.arcticles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.databinding.FragmentArcticlesBinding
import com.example.recipesapp.presentation.arcticles.ArcticlesViewModel

class ArcticlesFragment : BaseFragment<FragmentArcticlesBinding>() {

    private lateinit var viewModel: ArcticlesViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentArcticlesBinding {
        return FragmentArcticlesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}