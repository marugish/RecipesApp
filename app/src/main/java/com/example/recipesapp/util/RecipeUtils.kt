package com.example.recipesapp.util

import java.util.Locale
import kotlin.math.round


object RecipeUtils {

    /* Легкий: readyInMinutes ≤ 20,
               количество шагов ≤ 5,
               ингредиентов ≤ 5.
    Средний: readyInMinutes ≤ 45,
             шагов ≤ 10,
             ингредиентов ≤ 10.
    Сложный: readyInMinutes > 45,
             шагов > 10,
             ингредиентов > 10. */
    fun getDifficultyLevel(readyInMinutes: Int): DifficultyLevel {
        return when {
            readyInMinutes <= 20 -> DifficultyLevel.EASY
            readyInMinutes in 21..45 -> DifficultyLevel.MIDDLE
            else -> DifficultyLevel.HARD
        }
    }

    private fun convertPercentToFiveScale(score: Float): String {
        val percent = round(score).toInt()
        val rating = 1.0 + (percent / 100.0) * 4.0
        return String.format(Locale.US,"%.1f", rating)
    }

    fun getRecipeRating(score: Float, likes: Int): String {
        return convertPercentToFiveScale(score) + " ($likes+)"
    }
}