package com.example.recipesapp.domain.search.model

data class Recipe(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,

    // val veryPopular: Boolean,
    // val weightWatcherSmartPoints: Int,
    /*"preparationMinutes": null,
    "cookingMinutes": null,
    "aggregateLikes": 12,
    "healthScore": 23.0,
    "cuisines": [
    "Mediterranean",
    "European",
    "Greek"
    ],
    "dishTypes": [
    "lunch",
    "main course",
    "main dish",
    "dinner"
    ],
    "spoonacularScore": 79.13444519042969,
    "spoonacularSourceUrl": "https://spoonacular.com/lamb-burgers-with-tzatziki-sauce-649195" */
)