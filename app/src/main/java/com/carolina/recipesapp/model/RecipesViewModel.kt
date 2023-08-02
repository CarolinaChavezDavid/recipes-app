package com.carolina.recipesapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolina.recipesapp.data.RecipesResponse
import com.carolina.recipesapp.data.Resource
import com.carolina.recipesapp.repository.RecipesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RecipesViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {
    val recipes: MutableLiveData<Resource<RecipesResponse>> = MutableLiveData()

    init {
        getRecipes()
    }

    private fun getRecipes() = viewModelScope.launch {
        val response = recipesRepository.getRecipes()
        recipes.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<RecipesResponse>): Resource<RecipesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
