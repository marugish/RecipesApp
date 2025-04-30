package com.example.recipesapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.SearchItemBinding
import com.example.recipesapp.domain.search.model.Recipe

@SuppressLint("NotifyDataSetChanged")
class SearchRecipeAdapter(
    private var recipes: List<Recipe> = emptyList(),
    private val clickListener: (Recipe) -> Unit,
) : RecyclerView.Adapter<SearchRecipeViewHolder> () {

    fun updateRecipesList(items: List<Recipe>) {
        recipes = items
        notifyDataSetChanged()
    }

    /*fun addRecommendationsList(items: List<Vacancy>) {
        val startPosition = vacancies.size
        val newList = vacancies + items
        vacancies = newList
        notifyItemRangeInserted(startPosition, items.size)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipeViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return SearchRecipeViewHolder(
            SearchItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchRecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnClickListener {
            clickListener(recipes[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}