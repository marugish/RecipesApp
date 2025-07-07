package com.example.recipesapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.IngredientItemBinding
import com.example.recipesapp.domain.search.model.Ingredient

@SuppressLint("NotifyDataSetChanged")
class IngredientsAdapter(
    private var ingredients: List<Ingredient> = emptyList(),
    //private val clickListener: (Ingredient) -> Unit
) : RecyclerView.Adapter<IngredientsViewHolder>() {

    fun updateIngredientsList(items: List<Ingredient>) {
        ingredients = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return IngredientsViewHolder(
            IngredientItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredients[position])
        /*holder.itemView.setOnClickListener {
            clickListener(ingredients[position])
            notifyDataSetChanged()
        }*/
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}