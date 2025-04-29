package com.example.recipesapp.data.network

import com.example.recipesapp.data.dto.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("results") val recipesList: List<RecipeDto>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("number") val number: Int,
    @SerializedName("totalResults") val totalResults: Int
)