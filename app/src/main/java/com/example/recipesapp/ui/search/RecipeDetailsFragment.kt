package com.example.recipesapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.example.recipesapp.presentation.search.RecipeDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private val viewModel by viewModel<RecipeDetailsViewModel>()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRecipeDetailsBinding {
        return FragmentRecipeDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //popBackStack или navigateUp()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Кнопка Back
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

}