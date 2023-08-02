package com.carolina.recipesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.model.RecipesListViewModel
import com.carolina.recipesapp.navigation.AppNavigation
import com.carolina.recipesapp.ui.screens.recipeExmaple
import com.carolina.recipesapp.ui.theme.RecipesAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var viewModel: RecipesListViewModel
    var recipes: List<Recipe> = listOf(recipeExmaple, recipeExmaple, recipeExmaple)

    private var recipesList: List<Recipe> = recipes

    private val TAG = "RECIPES ERROR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecipesAPPTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(viewModel: RecipesListViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val getRecipeDate = viewModel.getRecipesData.observeAsState()
    Log.i("carolina", "$getRecipeDate")

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
        ) {
            if (viewModel.isLoading.value) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            if (!viewModel.isLoading.value) {
                getRecipeDate.value?.let { recipes -> AppNavigation(recipes) }
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
