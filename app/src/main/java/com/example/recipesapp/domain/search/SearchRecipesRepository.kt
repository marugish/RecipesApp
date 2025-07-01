package com.example.recipesapp.domain.search

import com.example.recipesapp.domain.search.model.RecipesStateLoad
import com.example.recipesapp.domain.search.model.RecommendationsStateLoad
import kotlinx.coroutines.flow.Flow

interface SearchRecipesRepository {
    fun searchRecipes(
        query: String,
        offset: Int,
        number: Int,
        addRecipeInformation: Boolean,
        fillIngredients: Boolean
    ): Flow<RecipesStateLoad>

    fun searchRecommendations(number: Int): Flow<RecommendationsStateLoad>
}