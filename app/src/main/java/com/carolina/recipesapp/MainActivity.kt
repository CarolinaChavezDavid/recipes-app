package com.carolina.recipesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.carolina.recipesapp.model.RecipesListViewModel
import com.carolina.recipesapp.navigation.AppNavigation
import com.carolina.recipesapp.ui.theme.RecipesAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                getRecipeDate.value?.let { recipes -> AppNavigation(recipes, viewModel) }
            }
        }
    }
}
