package com.example.recipesapp.presentation.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.domain.search.SearchRecipesInteractor
import com.example.recipesapp.domain.search.model.RecipesSearchResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRecipesInteractor: SearchRecipesInteractor,
    private val context: Application
) : ViewModel() {

    private var searchJob: Job? = null
    private var latestSearchText: String? = null

    // Поиск рецептов
    private val _searchScreenState = MutableLiveData<RecipesScreenState>()
    val searchScreenState: LiveData<RecipesScreenState> = _searchScreenState

    init {
        // тут будет вызов рекомендаций и список фильтров
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY_IN_MLS)
            searchRecipes(changedText)
        }
    }

    private fun searchRecipes(request: String) {
        if (request.isNotEmpty()) {
            //renderState(TracksState.Loading) ??????

            viewModelScope.launch {
                searchRecipesInteractor.searchRecipes(
                        query = request,
                        offset = 0,
                        number = 10, // возможно, нужно сделать null, если без пагинации
                        addRecipeInformation = true,
                        fillIngredients = true, // ингредиенты
                        addRecipeInstructions = true // инструкции
                    )

                    .collect { searchRecipesResult ->
                        // из состояния Domain в Presentation
                        val state: RecipesScreenState = handleState(searchRecipesResult)
                        // обновление состояния экрана
                        setScreenState(state)
                    }
            }
        }
    }

    private fun handleState(searchRecipesResult: RecipesSearchResult): RecipesScreenState {
        val state: RecipesScreenState =
            when (searchRecipesResult) {
                is RecipesSearchResult.Loading -> RecipesScreenState.Loading
                is RecipesSearchResult.Success -> {
                    // это для пагинации
                    //totalPages = searchVacanciesResult.vacanciesFound.maxPages
                    //oldList = oldList + searchVacanciesResult.vacanciesFound.vacanciesList
                    RecipesScreenState.Content(
                        recipesList = searchRecipesResult.recipesFound.recipesList, //oldList,
                        foundRecipesCount = searchRecipesResult.recipesFound.totalResults
                    )
                }
                is RecipesSearchResult.NothingFound -> RecipesScreenState.NothingFound
                is RecipesSearchResult.Error ->  {
                    RecipesScreenState.Error(
                        errorText = context.getString(searchRecipesResult.error.messageRes),
                        errorImageId = searchRecipesResult.error.imageRes
                    )
                }

            }
        return state
    }

    private fun setScreenState(newState: RecipesScreenState) {
        _searchScreenState.postValue(newState)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY_IN_MLS = 2000L
    }
}