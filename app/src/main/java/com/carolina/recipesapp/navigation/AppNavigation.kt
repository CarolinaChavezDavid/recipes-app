package com.carolina.recipesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.ui.screens.DetailScreen
import com.carolina.recipesapp.ui.screens.HomeScreen

@Composable
fun AppNavigation(recipesList: List<Recipe>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScree.route) {
        composable(route = AppScreens.HomeScree.route) {
            HomeScreen(onNavigateToDetail = {
                navController.navigate("${AppScreens.DetailScreen.route}/$it")
            }, recipes = recipesList)
        }
        composable(
            route = "${AppScreens.DetailScreen.route}/{recipe_id}",
            arguments = listOf(
                navArgument("recipe_id") {
                    type = NavType.StringType
                },
            ),
        ) {
            val param = it.arguments?.getString("recipe_id") ?: ""
            DetailScreen(recipeId = param, navController = navController, recipes = recipesList)
        }
    }
}
