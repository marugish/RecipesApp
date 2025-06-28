package com.example.recipesapp.ui.search

import androidx.core.widget.addTextChangedListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.presentation.search.SearchViewModel
import com.example.recipesapp.databinding.FragmentSearchBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.presentation.search.RecipesScreenState
import com.example.recipesapp.util.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val adapter = SearchRecipeAdapter(clickListener = { recipe -> showRecipeDetail(recipe) })
    private fun showRecipeDetail(recipe: Recipe) {
        val bundle = Bundle()
        bundle.putInt("recipe", recipe.id)
        //findNavController().navigate(R.id.action_searchFragment_to_recipeFragment, bundle)
    }

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        // Edit Text
        binding.searchTextInput.addTextChangedListener(
            afterTextChanged = { s ->
                if (s.isNullOrEmpty()) {
                    // в отдельную функцию
                    binding.trendingSearchLayout.visibility = View.VISIBLE
                    binding.recommendationLayout.visibility = View.VISIBLE

                } else {
                    // в отдельную функцию
                    binding.trendingSearchLayout.visibility = View.GONE
                    binding.recommendationLayout.visibility = View.GONE

                }
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
                /*binding.searchClearButton.visibility = clearButtonVisibility(s)
                searchQuery = s.toString()
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
                if (binding.searchEditText.hasFocus()) {
                    if (s?.isEmpty() == true) {
                        val historyState = viewModel.observeHistoryState().value
                        if (historyState is HistoryState.Content) {
                            showHistory(historyState.tracks)
                        }
                    } else {
                        historyVisibility(View.GONE)
                    }
                }*/
            }
        )

        // подписаться на обновления от ViewModel
        viewModel.searchScreenState.observe(viewLifecycleOwner) {
            render(it)
        }


            /*binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    setSearchIcon()
                    binding.searchPlaceholder.visibility = View.VISIBLE
                    recyclerViewVisibility(false)
                    vacancyCountVisibility(false)
                    errorMessageVisibility()
                    isPaginationLoader = false
                } else {
                    setClearIcon()
                    binding.searchPlaceholder.visibility = View.GONE
                    isPaginationLoader = false
                    adapter.updateVacancyList(emptyList())
                    errorMessageVisibility()
                    vacancyCountVisibility()
                }
                viewModel.searchVacancies(searchedText = s.toString())
            }
        })*/

    }

    private fun render(state: RecipesScreenState) {
        when (state) {
            is RecipesScreenState.Loading -> showLoading()
            is RecipesScreenState.Content -> showContent(state.recipesList, state.foundRecipesCount)
            is RecipesScreenState.NothingFound -> showEmpty()

            // подумать про ошибки
            // не нужно столько состояний!!!!!!!
            is RecipesScreenState.Error -> showError(state.errorText)
            //is RecipesScreenState.InternalServerError -> showError(state.errorText)
            //is RecipesScreenState.UnknownError -> showError(state.errorText)
        }
    }

    private fun showContent(recipesList: List<Recipe>, foundRecipesCount: Int) {
        adapter.updateRecipesList(recipesList)
        recyclerViewVisibility(isShown = true)
        progressBarContentVisibility()

        //recipesCountVisibility(isShown = true, count = foundVacanciesCount)
        //progressBarPaginationVisibility()
        //errorMessageVisibility()
    }

    private fun showLoading() {
        progressBarContentVisibility(isShown = true)
        hideKeyboard()
    }

    private fun showEmpty() {
        TODO("Not yet implemented")
    }

    private fun showError(errorText: String) {
        errorLayoutVisibility(isShown = true)
        binding.errorText.text = errorText
        // + картинка
    }

    private fun recyclerViewVisibility(isShown: Boolean = false) {
        binding.searchLayout.isVisible = isShown
    }

    private fun progressBarContentVisibility(isShown: Boolean = false) {
        binding.progressBarContent.isVisible = isShown
    }

    private fun errorLayoutVisibility(isShown: Boolean = false) {
        binding.searchError.isVisible = isShown
    }

}