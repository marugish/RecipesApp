package com.example.recipesapp.di

import com.example.recipesapp.domain.search.SearchRecipesInteractor
import com.example.recipesapp.domain.search.impl.SearchRecipesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    // Search Recipes
    single<SearchRecipesInteractor> {
        SearchRecipesInteractorImpl(get())
    }
}