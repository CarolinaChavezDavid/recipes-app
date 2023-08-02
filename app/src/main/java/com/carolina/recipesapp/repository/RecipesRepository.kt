package com.carolina.recipesapp.repository

import com.carolina.recipesapp.data.RetrofitInstance

class RecipesRepository {
    suspend fun getRecipes() = RetrofitInstance.api.getRecipes()
}
