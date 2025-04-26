package com.example.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("preparationMinutes") val preparationMinutes: Int?,
    @SerializedName("cookingMinutes") val cookingMinutes: Int?,
    @SerializedName("aggregateLikes") val likes: Int,
    @SerializedName("healthScore") val healthScore: Int,
    @SerializedName("extendedIngredients") val ingredients: List<IngredientsDto>,
    @SerializedName("summary") val summary: String,
    @SerializedName("cuisines") val cuisines: List<String>,
    @SerializedName("dishTypes") val dishTypes: List<String>,
    @SerializedName("diets") val diets: List<String>,
    @SerializedName("instructions") val instructions: String,
    //@SerializedName("analyzedInstructions") val instructionsList: List<InstructionsDto>,
    @SerializedName("spoonacularScore") val score: Float
)



