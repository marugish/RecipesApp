package com.example.recipesapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.TypeMealItemBinding
import com.example.recipesapp.util.MealType

class TypeMealViewHolder(
    private val binding: TypeMealItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: MealType) {
        binding.mealTextView.text = model.apiValue // под вопросом
    }
}