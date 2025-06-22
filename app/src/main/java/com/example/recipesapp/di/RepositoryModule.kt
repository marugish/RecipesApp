package com.example.recipesapp.di

import com.example.recipesapp.data.impl.SearchRecipesRepositoryImpl
import com.example.recipesapp.domain.search.SearchRecipesRepository
import org.koin.dsl.module

val repositoryModule = module {
    // Search Recipes
    single<SearchRecipesRepository> {
        SearchRecipesRepositoryImpl(get())
    }
}