package com.carolina.recipesapp.data.network

import com.carolina.recipesapp.data.RecipesResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface RecipesAPI {
    companion object {
        const val BASE_URL = "https://demo2315901.mockable.io/"
    }

    @GET("recipes/")
    suspend fun getRecipes(): RecipesResponse
}
