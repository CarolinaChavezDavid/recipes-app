package com.carolina.recipesapp.repository

import com.carolina.recipesapp.data.RecipesResponse
import com.carolina.recipesapp.data.Resource
import com.carolina.recipesapp.data.network.RecipesAPI
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RecipesRepository @Inject constructor(
    private val apiInterface: RecipesAPI,
) {
    suspend fun getRecipes(): Resource<RecipesResponse> {
        val response = try {
            apiInterface.getRecipes()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}
