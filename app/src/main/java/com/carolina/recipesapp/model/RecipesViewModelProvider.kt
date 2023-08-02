package com.carolina.recipesapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolina.recipesapp.repository.RecipesRepository

class RecipesViewModelProvider(private val recipesRepository: RecipesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipesViewModel(recipesRepository) as T
    }
}
