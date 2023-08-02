package com.carolina.recipesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes"
)
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val image: String,
    val ingredients: List<String>,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val recipeId: String,
    val shortDescription: String,
    val steps: List<String>,
)
