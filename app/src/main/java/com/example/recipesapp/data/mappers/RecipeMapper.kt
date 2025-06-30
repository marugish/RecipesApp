package com.example.recipesapp.data.mappers

import com.example.recipesapp.data.dto.IngredientDto
import com.example.recipesapp.data.dto.RecipeDto
import com.example.recipesapp.data.network.RecipesResponse
import com.example.recipesapp.domain.search.model.Ingredient
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.domain.search.model.RecipesFound

fun RecipesResponse.toDomain(): RecipesFound {
    return RecipesFound(
        recipesList = recipesList.map { it.toDomain() },
        offset = offset,
        number = number,
        totalResults = totalResults
    )
}

private fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = id,
        image = imageUrl,
        imageType = imageType,
        title = title,
        readyInMinutes = readyInMinutes,
        servings = servings,
        summary = summary,
        score = score,
        likes = likes,
        ingredientsList = ingredients.map { it.toDomain() }
    )
}

private fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(
        id = id,
        image = image,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        amount = amount,
        unit = unit
    )
}