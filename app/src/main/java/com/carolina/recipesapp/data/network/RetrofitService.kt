package com.carolina.recipesapp.data.network

import com.carolina.recipesapp.data.network.RecipesAPI.Companion.BASE_URL
import com.carolina.recipesapp.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitService {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        api: RecipesAPI,
    ) = RecipesRepository(api)

    @Singleton
    @Provides
    fun providesRecipesApi(): RecipesAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RecipesAPI::class.java)
    }
}
