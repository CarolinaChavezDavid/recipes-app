package com.carolina.recipesapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.carolina.recipesapp.model.RecipeDataModel
import com.carolina.recipesapp.navigation.NavigationHost
import com.carolina.recipesapp.navigation.NavigationItems
import com.carolina.recipesapp.navigation.RecipesBottomNavigation
import com.carolina.recipesapp.ui.components.RecipeGridCard

val recipeExmaple = RecipeDataModel(
    id = "r1",
    name = "Classic Spaghetti Bolognese",
    image = "https://www.unileverfoodsolutions.com.co/dam/global-ufs/mcos/nola/colombia/calcmenu/recipes/CO-recipes/pasta-dishes/pasta-en-salsa-bolognesa/main-header.jpg",
    description = "A traditional Italian pasta dish with rich meaty Bolognese sauce.",
    ingredients = listOf(
        "Spaghetti",
        "Ground beef",
        "Tomatoes",
        "Onion",
        "Garlic",
        "Carrot",
        "Celery",
        "Tomato paste",
        "Red wine",
        "Olive oil",
    ),
    steps = listOf(
        "Boil spaghetti until al dente.",
        "Sautee onions, garlic, carrot, and celery.",
        "Add ground beef and cook until browned.",
        "Stir in tomatoes, tomato paste, and red wine.",
        "Simmer sauce until thickened. Serve over spaghetti.",
    ),
    location = Pair(4.6486251, -74.2478965),

)

val recipes = listOf(recipeExmaple, recipeExmaple, recipeExmaple, recipeExmaple, recipeExmaple)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val navigationItem = listOf(
        NavigationItems.Screen1,
        NavigationItems.Screen2,
        NavigationItems.Screen3,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { RecipesBottomNavigation(navController, navigationItem) },
    ) {
        Text(text = "Holi Caro")
        LazyColumn {
            items(recipes) {
                RecipeGridCard(it)
            }
        }
        NavigationHost(navController)
    }
}
