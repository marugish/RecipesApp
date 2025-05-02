package com.example.recipesapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.RecommendationItemBinding
import com.example.recipesapp.domain.search.model.Recipe

@SuppressLint("NotifyDataSetChanged")
class RecommendationAdapter(
    private var recommendations: List<Recipe> = emptyList(),
    private val clickListener: (Recipe) -> Unit,
) : RecyclerView.Adapter<RecommendationViewHolder> () {

    fun updateRecommendationList(items: List<Recipe>) {
        recommendations = items
        notifyDataSetChanged()
    }

    /*fun addRecommendationsList(items: List<Vacancy>) {
        val startPosition = vacancies.size
        val newList = vacancies + items
        vacancies = newList
        notifyItemRangeInserted(startPosition, items.size)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return RecommendationViewHolder(
            RecommendationItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(recommendations[position])
        holder.itemView.setOnClickListener {
            clickListener(recommendations[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }
}