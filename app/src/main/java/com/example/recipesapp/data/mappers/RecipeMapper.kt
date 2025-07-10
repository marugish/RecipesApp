package com.example.recipesapp.data.mappers

import com.example.recipesapp.data.dto.IngredientDto
import com.example.recipesapp.data.dto.InstructionsDto
import com.example.recipesapp.data.dto.MeasureUnitDto
import com.example.recipesapp.data.dto.MeasuresDto
import com.example.recipesapp.data.dto.RecipeDto
import com.example.recipesapp.data.dto.StepDto
import com.example.recipesapp.data.network.RecipesResponse
import com.example.recipesapp.data.network.RecommendationsResponse
import com.example.recipesapp.domain.search.model.Ingredient
import com.example.recipesapp.domain.search.model.Instructions
import com.example.recipesapp.domain.search.model.MeasureUnit
import com.example.recipesapp.domain.search.model.Measures
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.domain.search.model.RecipesFound
import com.example.recipesapp.domain.search.model.RecommendationsFound
import com.example.recipesapp.domain.search.model.Step

fun RecipesResponse.toDomain(): RecipesFound {
    return RecipesFound(
        recipesList = recipesList.map { it.toDomain() },
        offset = offset,
        number = number,
        totalResults = totalResults
    )
}

fun RecommendationsResponse.toDomain(): RecommendationsFound {
    return RecommendationsFound(
        recipesList = recipesList.map { it.toDomain() }
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
        ingredientsList = ingredients.map { it.toDomain() },
        instructionsList = instructions?.map { it.toDomain() } ?: emptyList()
    )
}

private fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(
        id = id,
        image = image ?: "",
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        amount = amount,
        unit = unit,
        measures = measures.toDomain()
    )
}

private fun MeasuresDto.toDomain() : Measures {
    return Measures(
        us = us.toDomain(),
        metric = metric.toDomain()
    )
}

private fun MeasureUnitDto.toDomain() : MeasureUnit {
    return MeasureUnit(
        amount = amount,
        unitShort = unitShort,
        unitLong = unitLong
    )
}

private fun InstructionsDto.toDomain(): Instructions {
    return Instructions(
        name = name,
        steps = steps?.map { it.toDomain() } ?: emptyList()
    )
}

private fun StepDto.toDomain(): Step {
    return Step(
        number = number,
        step = step
    )
}