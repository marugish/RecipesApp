package com.example.recipesapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.recipesapp.databinding.RecommendationItemBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.R
import com.example.recipesapp.util.RecipeUtils.getRecipeRating
import com.example.recipesapp.util.RecipeUtils.getDifficultyLevel

class RecommendationViewHolder(
    private val binding: RecommendationItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Recipe) {
        binding.recipeName.text = model.title
        binding.rating.text = getRecipeRating(model.score, model.likes)
        binding.difficultyLevel.text = getDifficultyLevel(
            model.readyInMinutes,
            model.ingredientsList.size
        ).label

        // 312x231, 480x360
        fun getCoverArtwork() = model.image.replaceAfterLast('-',"312x231.jpg")
        Glide.with(itemView.context)
            .load(getCoverArtwork())
            .placeholder(R.drawable.food_placeholder)
            .centerCrop()
            .transform(RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius_image)))
            .into(binding.recipeImage)

    }
}