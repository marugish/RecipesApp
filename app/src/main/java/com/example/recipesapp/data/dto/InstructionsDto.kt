package com.example.recipesapp.data.dto

data class InstructionsDto(
    val name: String,
    val steps: List<StepDto>?
)