package com.example.recipesapp.domain.search.model

import com.example.recipesapp.util.AppError

sealed class RecipesStateLoad {
    data class Success(val recipesFound: RecipesFound) : RecipesStateLoad()
    data class Error(val error: AppError) : RecipesStateLoad()
    data object Loading : RecipesStateLoad()
}