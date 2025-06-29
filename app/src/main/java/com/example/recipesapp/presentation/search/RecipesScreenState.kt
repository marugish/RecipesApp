package com.example.recipesapp.presentation.search

import com.example.recipesapp.domain.search.model.Recipe

sealed class RecipesScreenState {
    data object Loading: RecipesScreenState()
    data class Content(
        val recipesList: List<Recipe>,
        val foundRecipesCount: Int
    ) : RecipesScreenState()
    data object NothingFound: RecipesScreenState()
    data class Error(val errorText: String, val errorImageId: Int): RecipesScreenState()
}