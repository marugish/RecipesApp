package com.example.recipesapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.RecommendationItemBinding
import com.example.recipesapp.databinding.TypeMealItemBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.util.MealType

@SuppressLint("NotifyDataSetChanged")
class TypeMealAdapter(
    private var meals: List<MealType> = emptyList(),
    private val clickListener: (MealType) -> Unit,
) : RecyclerView.Adapter<TypeMealViewHolder> () {

    fun updateMealsList(items: List<MealType>) {
        meals = items
        notifyDataSetChanged()
    }

    /*fun addRecommendationsList(items: List<Vacancy>) {
        val startPosition = vacancies.size
        val newList = vacancies + items
        vacancies = newList
        notifyItemRangeInserted(startPosition, items.size)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeMealViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return TypeMealViewHolder(
            TypeMealItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TypeMealViewHolder, position: Int) {
        holder.bind(meals[position])
        holder.itemView.setOnClickListener {
            clickListener(meals[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}