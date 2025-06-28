package com.example.recipesapp.domain.search.model

import com.example.recipesapp.util.AppError

sealed class RecipesSearchResult {
    data object Loading : RecipesSearchResult()
    data class Success(val recipesFound: RecipesFound) : RecipesSearchResult()
    data object NothingFound : RecipesSearchResult()
    data class Error(val error: AppError/*val errorCode: Int, val message: String*/) : RecipesSearchResult()
}