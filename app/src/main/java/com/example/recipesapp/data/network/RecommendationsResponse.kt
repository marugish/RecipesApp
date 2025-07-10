package com.example.recipesapp.data.network

import com.example.recipesapp.data.dto.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(
    @SerializedName("recipes") val recipesList: List<RecipeDto>
)