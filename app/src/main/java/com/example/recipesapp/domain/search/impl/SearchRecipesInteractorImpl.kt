package com.example.recipesapp.domain.search.impl

import com.example.recipesapp.domain.search.SearchRecipesInteractor
import com.example.recipesapp.domain.search.SearchRecipesRepository
import com.example.recipesapp.domain.search.model.RecipesSearchResult
import com.example.recipesapp.domain.search.model.RecipesStateLoad
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRecipesInteractorImpl(private val searchRecipesRepository: SearchRecipesRepository): SearchRecipesInteractor {
    override fun searchRecipes(
        query: String,
        offset: Int,
        number: Int,
        addRecipeInformation: Boolean
    ): Flow<RecipesSearchResult> {

        val result = searchRecipesRepository.searchRecipes(
            query = query,
            offset = offset,
            number = number,
            addRecipeInformation = addRecipeInformation
        ).map { recipes ->
            when (recipes) {
                is RecipesStateLoad.Loading -> RecipesSearchResult.Loading
                is RecipesStateLoad.Error -> RecipesSearchResult.Error(
                    errorCode = recipes.error.code,
                    message = recipes.error.message
                )
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

}