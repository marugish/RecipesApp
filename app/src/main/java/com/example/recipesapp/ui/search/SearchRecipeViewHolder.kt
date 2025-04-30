package com.example.recipesapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.recipesapp.R
import com.example.recipesapp.databinding.SearchItemBinding
import com.example.recipesapp.domain.search.model.Recipe

class SearchRecipeViewHolder(
    private val binding: SearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Recipe) {
        binding.recipeName.text = model.title
        //binding.rating.text = model.
        //binding.level.text =
        // binding.summary.text =


        //val logoUrl: String = model.image
        Glide.with(itemView.context)
            .load(model.image)
            .placeholder(R.drawable.food_placeholder)
            .centerCrop()
            .transform(RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius_image)))
            .into(binding.recipeImage)

    }
}