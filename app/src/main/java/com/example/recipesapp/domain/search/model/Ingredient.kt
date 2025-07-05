package com.example.recipesapp.domain.search.model

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
    /*val measures: Measures*/ // пока убрала
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
