package com.example.recipesapp.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.databinding.FragmentArcticlesBinding
import com.example.recipesapp.presentation.articles.ArticlesViewModel

class ArticlesFragment : BaseFragment<FragmentArcticlesBinding>() {

    private lateinit var viewModel: ArticlesViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentArcticlesBinding {
        return FragmentArcticlesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}