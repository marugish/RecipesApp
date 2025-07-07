package com.example.recipesapp.domain.search

import com.example.recipesapp.domain.search.model.RecipesSearchResult
import com.example.recipesapp.domain.search.model.RecommendationsResult
import kotlinx.coroutines.flow.Flow

interface SearchRecipesInteractor {
    fun searchRecipes(
        query: String,
        offset: Int,
        number: Int,
        addRecipeInformation: Boolean,
        fillIngredients: Boolean,
        addRecipeInstructions: Boolean
    ): Flow<RecipesSearchResult>

    fun searchRecommendations(number: Int): Flow<RecommendationsResult>
}