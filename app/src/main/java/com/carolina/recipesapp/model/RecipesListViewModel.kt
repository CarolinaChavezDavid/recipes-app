package com.carolina.recipesapp.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.data.Resource
import com.carolina.recipesapp.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val repository: RecipesRepository,
) : ViewModel() {

    var isLoading = mutableStateOf(true)
    private var _getRecipesData: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    var getRecipesData: LiveData<List<Recipe>> = _getRecipesData

    init {
        getRecipesData()
    }

    fun getRecipesData() = viewModelScope.launch {
        val response = repository.getRecipes()
        if (response is Resource.Success) {
            isLoading.value = false
            _getRecipesData.value = response.data?.recipes
        }
    }

    fun searchRecipe(query: String) {
        val foundRecipes = _getRecipesData.value?.filter { recipe ->
            recipe.name.contains(query)
        }
        _getRecipesData.postValue(foundRecipes ?: listOf())
    }
}
