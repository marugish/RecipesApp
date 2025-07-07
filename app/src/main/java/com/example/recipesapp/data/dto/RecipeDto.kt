package com.example.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

// что-то из этого может быть пустым
// сделать для тех параметров, которые будут false
data class RecipeDto(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("summary") val summary: String,
    @SerializedName("spoonacularScore") val score: Float,
    @SerializedName("aggregateLikes") val likes: Int,
    @SerializedName("extendedIngredients") val ingredients: List<IngredientDto>,
    @SerializedName("analyzedInstructions") val instructions: List<InstructionsDto>?
)

// val veryPopular: Boolean,
// val weightWatcherSmartPoints: Int,
/*"preparationMinutes": null,
"cookingMinutes": null,
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
*/