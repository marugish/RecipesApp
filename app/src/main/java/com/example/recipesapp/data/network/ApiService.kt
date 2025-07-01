package com.example.recipesapp.data.network

import com.example.recipesapp.data.dto.JokeDto
import com.example.recipesapp.data.dto.RecipeDetailsDto
import com.example.recipesapp.data.dto.TriviaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Обычный поиск
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("number") number: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("fillIngredients") fillIngredients: Boolean
    ): Response<RecipesResponse>

    // Поиск рекомендаций
    @GET("recipes/random")
    suspend fun searchRecommendations(@Query("number") number: Int): Response<RecommendationsResponse>

    // почитать про suspend
    // почитать про QueryMap

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(@Path("id") recipeId: Int): Response<RecipeDetailsDto>

    @GET("food/jokes/random")
    suspend fun getFoodJoke(): Response<JokeDto>

    @GET("food/trivia/random")
    suspend fun getFoodTrivia(): Response<TriviaDto>

    // Статьи на сайте
    // ...

    // Видео
    // ...
}