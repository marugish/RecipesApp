package com.example.recipesapp.ui.search

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.recipesapp.R
import com.example.recipesapp.databinding.SearchItemBinding
import com.example.recipesapp.domain.search.model.Recipe
import com.example.recipesapp.util.RecipeUtils.getDifficultyLevel
import com.example.recipesapp.util.RecipeUtils.getRecipeRating

class SearchRecipeViewHolder(
    private val binding: SearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Recipe) {
        binding.recipeName.text = model.title
        binding.rating.text = getRecipeRating(model.score, model.likes) // String.format("%d (%d%%)", model.likes, model.score.toInt())

        // не до конца высчитала
        binding.difficultyLevel.text = getDifficultyLevel(model.readyInMinutes).label

        binding.summary.text = Html.fromHtml(model.summary, Html.FROM_HTML_MODE_LEGACY)


        //val logoUrl: String = model.image
        Glide.with(itemView.context)
            .load(model.image)
            .placeholder(R.drawable.food_placeholder)
            .centerCrop()
            .transform(RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.corner_radius_image)))
            .into(binding.recipeImage)

    }
}