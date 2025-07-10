package com.example.recipesapp.domain.search.model

import com.example.recipesapp.util.AppError

sealed class RecommendationsStateLoad {
    data class Success(val recipesFound: RecommendationsFound) : RecommendationsStateLoad()
    data class Error(val error: AppError) : RecommendationsStateLoad()
    data object Loading : RecommendationsStateLoad()
}