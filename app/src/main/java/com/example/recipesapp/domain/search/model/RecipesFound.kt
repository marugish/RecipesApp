package com.example.recipesapp.domain.search.model

data class RecipesFound(
    val recipesList: List<Recipe>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
