package com.carolina.recipesapp.model

data class RecipeDataModel(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val location: Pair<Double, Double>,
)
