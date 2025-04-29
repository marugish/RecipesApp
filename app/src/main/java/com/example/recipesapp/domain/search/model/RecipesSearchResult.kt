package com.example.recipesapp.domain.search.model

sealed class RecipesSearchResult {
    data object Loading : RecipesSearchResult()
    data class Success(val recipesFound: RecipesFound) : RecipesSearchResult()
    data object NothingFound : RecipesSearchResult()
    data class Error(val errorCode: Int, val message: String) : RecipesSearchResult()
}