package com.example.recipesapp.domain.search.impl

import com.example.recipesapp.domain.search.SearchRecipesInteractor
import com.example.recipesapp.domain.search.SearchRecipesRepository
import com.example.recipesapp.domain.search.model.RecipesSearchResult
import com.example.recipesapp.domain.search.model.RecipesStateLoad
import com.example.recipesapp.domain.search.model.RecommendationsResult
import com.example.recipesapp.domain.search.model.RecommendationsStateLoad
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRecipesInteractorImpl(private val searchRecipesRepository: SearchRecipesRepository): SearchRecipesInteractor {
    override fun searchRecipes(
        query: String,
        offset: Int,
        number: Int,
        addRecipeInformation: Boolean,
        fillIngredients: Boolean,
        addRecipeInstructions: Boolean
    ): Flow<RecipesSearchResult> {

        val result = searchRecipesRepository.searchRecipes(
            query = query,
            offset = offset,
            number = number,
            addRecipeInformation = addRecipeInformation,
            fillIngredients = fillIngredients,
            addRecipeInstructions = addRecipeInstructions
        ).map { recipes ->
            when (recipes) {
                is RecipesStateLoad.Loading -> RecipesSearchResult.Loading
                is RecipesStateLoad.Error -> RecipesSearchResult.Error(error = recipes.error)
                is RecipesStateLoad.Success -> {
                    if (recipes.recipesFound != null && recipes.recipesFound.recipesList.isNotEmpty()) {
                        RecipesSearchResult.Success(recipesFound = recipes.recipesFound)
                    } else {
                        RecipesSearchResult.NothingFound
                    }
                }
            }
        }
        return result
    }

    override fun searchRecommendations(number: Int): Flow<RecommendationsResult> {
        return searchRecipesRepository.searchRecommendations(
            number = number
        ).map { recipes ->
            when(recipes) {
                is RecommendationsStateLoad.Loading -> RecommendationsResult.Loading
                is RecommendationsStateLoad.Error -> RecommendationsResult.Error(error = recipes.error)
                is RecommendationsStateLoad.Success -> {
                    if (recipes.recipesFound != null && recipes.recipesFound.recipesList.isNotEmpty()) {
                        RecommendationsResult.Success(recipesFound = recipes.recipesFound)
                    } else {
                        RecommendationsResult.NothingFound
                    }
                }

            }
        }
    }

}