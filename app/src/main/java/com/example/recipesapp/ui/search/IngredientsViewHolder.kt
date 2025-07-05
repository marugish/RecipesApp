package com.example.recipesapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.recipesapp.R
import com.example.recipesapp.databinding.IngredientItemBinding
import com.example.recipesapp.domain.search.model.Ingredient

class IngredientsViewHolder(
    private val binding: IngredientItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Ingredient) {
        binding.ingredientText.text = model.name // необходимо понять, какое название указать

        Glide.with(itemView.context)
            .load(model.image)
            .placeholder(R.drawable.ingredient_placeholder_2)
            .centerCrop()
            .transform(RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius_image)))
            .into(binding.ingredientImage)

    }
}