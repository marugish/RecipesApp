package com.example.recipesapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.presentation.search.RecipeDetailsViewModel
import com.example.recipesapp.util.RecipeUtils.getDifficultyLevel
import com.example.recipesapp.util.RecipeUtils.getRecipeRating
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private var recipe: Recipe? = null
    private val viewModel by viewModel<RecipeDetailsViewModel>()

    private val ingredientAdapter = IngredientsAdapter()
    private val methodAdapter = MethodAdapter()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRecipeDetailsBinding {
        return FragmentRecipeDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ингредиенты
        binding.ingredientRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.ingredientRecyclerView.adapter = ingredientAdapter

        // Способ приготовления
        binding.methodRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.methodRecyclerView.adapter = methodAdapter

        // Получаем аргументы
        arguments?.let { bundle ->
            recipe = bundle.getSerializable("recipe") as? Recipe
        }

        displayRecipeDetails(recipe)

        //popBackStack или navigateUp()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Кнопка Back
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun displayRecipeDetails(recipe: Recipe?) {
        if (recipe != null)
        {
            // Другой размер картинки нужен

            Glide.with(requireContext())
                .load(recipe.image)
                .placeholder(R.drawable.food_placeholder)
                .centerCrop()
                .into(binding.recipeImage)

            // Recipe name
            binding.recipeName.text = recipe.title
            // Ingredients
            ingredientAdapter.updateIngredientsList(recipe.ingredientsList)
            binding.ingredientRecyclerView.isVisible = true
            // Rating + level
            binding.rating.text = getRecipeRating(recipe.score, recipe.likes)
            binding.difficultyLevel.text = getDifficultyLevel(recipe.readyInMinutes, recipe.ingredientsList.size).label
        }


        // servings
        // ...
        // time
        // ...
        // method
        // ...
    }

}