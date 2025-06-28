package com.example.recipesapp.presentation.search

import com.example.recipesapp.domain.search.model.Recipe

sealed class RecipesScreenState {
    data object Loading: RecipesScreenState()
    data class Content(
        val recipesList: List<Recipe>,
        val foundRecipesCount: Int
    ) : RecipesScreenState()
    data object NothingFound: RecipesScreenState()
    // всевозможные ошибки
    /*data class BadRequestError(val errorText: String) : RecipesScreenState()
    data class UnauthorizedError(val errorText: String) : RecipesScreenState()
    data class PaymentRequiredError(val errorText: String) : RecipesScreenState()
    data class ForbiddenError(val errorText: String) : RecipesScreenState()
    data class NotFoundError(val errorText: String) : RecipesScreenState()
    data class InternalServerError(val errorText: String) : RecipesScreenState()
    data class UnknownError(val errorText: String) : RecipesScreenState()*/
    data class Error(val errorText: String): RecipesScreenState()

    // остальные...
    // ...
}