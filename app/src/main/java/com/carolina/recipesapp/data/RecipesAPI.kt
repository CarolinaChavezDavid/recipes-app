package com.carolina.recipesapp.data

import retrofit2.Response
import retrofit2.http.GET

interface RecipesAPI {
    companion object {
        const val BASE_URL = "https://demo2315901.mockable.io/"
    }

    @GET("recipes/")
    suspend fun getRecipes(): Response<RecipesResponse>
}
