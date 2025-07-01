package com.example.recipesapp.ui.search

import androidx.core.widget.addTextChangedListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.R
import com.example.recipesapp.presentation.search.SearchViewModel
import com.example.recipesapp.databinding.FragmentSearchBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.presentation.search.RecipesScreenState
import com.example.recipesapp.util.RecipeUtils
import com.example.recipesapp.util.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val adapter = SearchRecipeAdapter(clickListener = { recipe -> showRecipeDetail(recipe) })
    private val recommendationAdapter = RecommendationAdapter(clickListener = { recipe -> showRecipeDetail(recipe) })
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

        // Обычный поиск
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        // Рекомендации
        binding.recommendationRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recommendationRecyclerView.adapter = recommendationAdapter

        // Edit Text
        binding.searchTextInput.addTextChangedListener(
            afterTextChanged = { s ->
                if (s.isNullOrEmpty()) {
                    trendingAndRecommendationVisibility(isShown = true)

                    recyclerViewVisibility()
                    errorLayoutVisibility()
                } else {
                    trendingAndRecommendationVisibility()

                    notFoundLayoutVisibility()
                    adapter.updateRecipesList(emptyList())
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

        // Рекомендации
        viewModel.recommendationScreenState.observe(viewLifecycleOwner) {
            recommendationRender(it)
        }

    }

    private fun render(state: RecipesScreenState) {
        when (state) {
            is RecipesScreenState.Loading -> showLoading()
            is RecipesScreenState.Content -> showContent(state.recipesList, state.foundRecipesCount)
            is RecipesScreenState.NothingFound -> showEmpty()
            is RecipesScreenState.Error -> showError(state.errorText, state.errorImageId)
        }
    }

    private fun showContent(recipesList: List<Recipe>, foundRecipesCount: Int) {
        adapter.updateRecipesList(recipesList)
        recyclerViewVisibility(isShown = true)
        progressBarContentVisibility()
        errorLayoutVisibility()

        notFoundLayoutVisibility()
        recipesCountVisibility(foundRecipesCount)

        //progressBarPaginationVisibility()
    }

    private fun recipesCountVisibility(count: Int) {
        binding.recipesCount.text = String.format(
            resources.getQuantityString(R.plurals.recipes_count, count),
            RecipeUtils.divideIntoDigits(count)
        )
    }

    private fun showLoading() {
        progressBarContentVisibility(isShown = true)
        hideKeyboard()
    }

    private fun showEmpty() {
        progressBarContentVisibility()
        notFoundLayoutVisibility(isShown = true)
    }

    private fun showError(errorText: String, errorImageId: Int) {
        progressBarContentVisibility()
        errorLayoutVisibility(isShown = true)
        binding.errorText.text = errorText
        binding.errorImage.setImageResource(errorImageId)
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

    private fun notFoundLayoutVisibility(isShown: Boolean = false) {
        binding.searchNotFound.isVisible = isShown
    }

    private fun trendingAndRecommendationVisibility(isShown: Boolean = false) {
        binding.trendingSearchLayout.isVisible = isShown
        binding.recommendationLayout.isVisible = isShown
    }

    private fun recommendationRender(state: RecipesScreenState) {
        when (state) {
            is RecipesScreenState.Loading -> {
                showLoading()
            }
            is RecipesScreenState.Content -> {
                showRecommendationContent(state.recipesList)
            }
            is RecipesScreenState.NothingFound -> {
                //showEmpty()
            }
            is RecipesScreenState.Error -> {
                //showError(state.errorText, state.errorImageId)
            }
        }
    }

    private fun showRecommendationContent(recipesList: List<Recipe>) {
        recommendationAdapter.updateRecommendationList(recipesList)
        recommendationRecyclerViewVisibility(isShown = true)
        progressBarContentVisibility()

        // под вопросом
        //errorLayoutVisibility()
        //notFoundLayoutVisibility()


        //progressBarPaginationVisibility()
    }

    private fun recommendationRecyclerViewVisibility(isShown: Boolean = false) {
        binding.recommendationLayout.isVisible = isShown
    }

}