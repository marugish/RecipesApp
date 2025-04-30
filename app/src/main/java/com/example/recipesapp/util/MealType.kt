package com.example.recipesapp.util

enum class MealType(val apiValue: String) {
    MAIN_COURSE("main course"),
    SIDE_DISH("side dish"),
    DESSERT("dessert"),
    APPETIZER("appetizer"),
    SALAD("salad"),
    BREAD("bread"),
    BREAKFAST("breakfast"),
    SOUP("soup"),
    BEVERAGE("beverage"),
    SAUCE("sauce"),
    MARINADE("marinade"),
    FINGERFOOD("fingerfood"),
    SNACK("snack"),
    DRINK("drink");

    companion object {
        fun fromApiValue(value: String): MealType? =
            entries.find { it.apiValue.equals(value, ignoreCase = true) }
    }
}
