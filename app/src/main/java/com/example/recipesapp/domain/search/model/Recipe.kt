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
    //val ingredientsList: List<Ingredient>,

    // val veryPopular: Boolean,
    // val weightWatcherSmartPoints: Int,
    /*"preparationMinutes": null,
    "cookingMinutes": null,
   */
)

data class Ingredient(
    val id: Int,
    //val aisle: String,
    val image: String,
    //val consistency: String,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
    //val meta: List<String>,
    val measures: Measures
)

data class Measures(
    val us: MeasureUnit,
    val metric: MeasureUnit
)

data class MeasureUnit(
    val amount: Double,
    val unitShort: String,
    val unitLong: String
)
