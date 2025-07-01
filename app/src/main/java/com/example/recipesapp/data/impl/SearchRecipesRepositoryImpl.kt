package com.example.recipesapp.data.impl

import android.content.Context
import com.example.recipesapp.data.mappers.toDomain
import com.example.recipesapp.data.network.ApiService
import com.example.recipesapp.data.network.call
import com.example.recipesapp.domain.search.SearchRecipesRepository
import com.example.recipesapp.util.AppError
import com.example.recipesapp.domain.search.model.RecipesStateLoad
import com.example.recipesapp.domain.search.model.RecommendationsStateLoad
import com.example.recipesapp.domain.search.model.Response
import com.example.recipesapp.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRecipesRepositoryImpl(
    private val apiService: ApiService,
    private val context: Context
) : SearchRecipesRepository {
    override fun searchRecipes(
        query: String,
        offset: Int,
        number: Int,
        addRecipeInformation: Boolean,
        fillIngredients: Boolean
    ): Flow<RecipesStateLoad> {
        return flow {
            // Попытка загрузки
            emit(RecipesStateLoad.Loading)
            // Проверяем сеть
            if (!NetworkUtils.isNetworkAvailable(context)) {
                emit(RecipesStateLoad.Error(AppError.NoInternet))
                return@flow
            }
            // Получаем данные
            val response = kotlin.runCatching {
                apiService.searchRecipes(
                    query = query,
                    offset = offset,
                    number = number,
                    addRecipeInformation = addRecipeInformation,
                    fillIngredients = fillIngredients
                ).call()
            }.getOrNull()
            // Обрабатываем результат
            emit(
                when (response) {
                    null -> RecipesStateLoad.Loading
                    is Response.Error -> RecipesStateLoad.Error(AppError.fromCode(response.errorCode))
                    is Response.Success -> RecipesStateLoad.Success(response.data.toDomain())
                }
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun searchRecommendations(number: Int): Flow<RecommendationsStateLoad> {
        return flow {
            // Попытка загрузки
            emit(RecommendationsStateLoad.Loading)
            // Проверяем сеть
            if (!NetworkUtils.isNetworkAvailable(context)) {
                emit(RecommendationsStateLoad.Error(AppError.NoInternet))
                return@flow
            }
            // Получаем данные
            val response = kotlin.runCatching {
                apiService.searchRecommendations(
                    number = number,
                ).call()
            }.getOrNull()
            // Обрабатываем результат
            emit(
                when (response) {
                    null -> RecommendationsStateLoad.Loading
                    is Response.Error -> RecommendationsStateLoad.Error(AppError.fromCode(response.errorCode))
                    is Response.Success -> RecommendationsStateLoad.Success(response.data.toDomain())
                }
            )
        }.flowOn(Dispatchers.IO)
    }

}