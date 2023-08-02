package com.carolina.recipesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.data.Resource
import com.carolina.recipesapp.model.RecipesViewModel
import com.carolina.recipesapp.model.RecipesViewModelProvider
import com.carolina.recipesapp.repository.RecipesRepository
import com.carolina.recipesapp.ui.screens.HomeScreen
import com.carolina.recipesapp.ui.screens.recipeExmaple
import com.carolina.recipesapp.ui.theme.RecipesAPPTheme

class MainActivity : ComponentActivity() {

    lateinit var viewModel: RecipesViewModel

    lateinit var recipes: List<Recipe>

    private val TAG = "RECIPES ERROR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = RecipesRepository()
        val viewModelProvider = RecipesViewModelProvider(repository)

        viewModel = ViewModelProvider(this, viewModelProvider)[RecipesViewModel::class.java]

        viewModel.recipes.observe(
            this,
        ) { response ->
            Log.i(TAG, "data: ${response.data}")
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        recipes = data.recipes
                        Log.i("carolina", "error: ${data.recipes}")
                    }
                }
                else -> {
                    recipes = listOf(recipeExmaple)
                    response.message?.let {
                        Log.e(TAG, "error: $it")
                    }
                }
            }
        }

        setContent {
            RecipesAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    HomeScreen(recipes)
                }
            }
        }
    }
}

@Composable
private fun CircularProgressAnimated() {
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900)),
    )

    CircularProgressIndicator(progress = progressAnimationValue)
}
