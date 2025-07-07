package com.example.recipesapp.domain.search.model

data class Ingredient(
    val id: Int,
    val image: String,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
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
