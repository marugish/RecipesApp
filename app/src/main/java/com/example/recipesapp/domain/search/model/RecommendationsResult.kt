package com.example.recipesapp.domain.search.model

import com.example.recipesapp.util.AppError

sealed class RecommendationsResult {
    data object Loading : RecommendationsResult()
    data class Success(val recipesFound: RecommendationsFound) : RecommendationsResult()
    data object NothingFound : RecommendationsResult()
    data class Error(val error: AppError) : RecommendationsResult()
}
