package com.example.recipesapp.di

import com.example.recipesapp.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Search Recipes
    viewModel {
        SearchViewModel(
            searchRecipesInteractor = get(),
        )
    }
}

