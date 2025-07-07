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

        binding.ingredientText.text = String.format("%s", model.original)/*String.format(
            "%d %s %s",
            model.measures.metric.amount.toInt(),
            model.measures.metric.unitShort,
            model.name
        ) */

        fun getCoverArtwork() = "https://img.spoonacular.com/ingredients_250x250/" + model.image
        Glide.with(itemView.context)
            .load(getCoverArtwork())    //model.image)
            .placeholder(R.drawable.ingredient_placeholder_2)
            .centerCrop()
            .transform(RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius_image)))
            .into(binding.ingredientImage)

    }
}