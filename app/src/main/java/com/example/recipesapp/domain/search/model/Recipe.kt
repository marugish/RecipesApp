package com.example.recipesapp.domain.search.model

data class Recipe(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val score: Float,
    val likes: Int,
    val ingredientsList: List<Ingredient>,

    // val veryPopular: Boolean,
    // val weightWatcherSmartPoints: Int,
    /*"preparationMinutes": null,
    "cookingMinutes": null,
   */
)