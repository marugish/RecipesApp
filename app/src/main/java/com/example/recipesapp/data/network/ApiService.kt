package com.example.recipesapp.data.network

import com.example.recipesapp.data.dto.JokeDto
import com.example.recipesapp.data.dto.RecipeDetailsDto
import com.example.recipesapp.data.dto.RecipeDto
import com.example.recipesapp.data.dto.TriviaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("number") number: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("fillIngredients") fillIngredients: Boolean,
        @Query("addRecipeInstructions") addRecipeInstructions: Boolean
    ): Response<RecipesResponse>

    // почитать про suspend
    // почитать про QueryMap


    // Для рекомендаций
    @GET("recipes/random")
    suspend fun searchRecommendation(@Query("number") number: Int): Response<RecipeDto>
    // тут возвращает структуру, что мы получаем при запросе отдельного рецепта по ID
    // однако, для рекомендаций нужна та же информация, что и от searchRecipes
    //?number=10&offset=0 - пока offset не указывала

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