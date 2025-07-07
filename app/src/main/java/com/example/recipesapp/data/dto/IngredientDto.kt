package com.example.recipesapp.data.dto

data class IngredientDto(
    val id: Int,
    val image: String?,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
    val measures: MeasuresDto
)

data class MeasuresDto(
    val us: MeasureUnitDto,
    val metric: MeasureUnitDto
)

data class MeasureUnitDto(
    val amount: Double,
    val unitShort: String,
    val unitLong: String
)


/*"id": 11011,
"aisle": "Produce",
"image": "asparagus.png",
"consistency": "SOLID",
"name": "spinach",
"nameClean": "asparagus",
"original": "8 smalls spinach or asparagus",
"originalName": "s spinach or asparagus",
"amount": 8.0,
"unit": "small",
"meta": [],
"measures": {
    "us": {
        "amount": 8.0,
        "unitShort": "small",
        "unitLong": "smalls"
    },
    "metric": {
        "amount": 8.0,
        "unitShort": "small",
        "unitLong": "smalls"
    }
}*/