package com.example.recipesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.databinding.FragmentArticlesTabBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesTabFragment : BaseFragment<FragmentArticlesTabBinding>() {

    private val viewModel by viewModel<ArticlesTabViewModel>()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentArticlesTabBinding {
        return FragmentArticlesTabBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}