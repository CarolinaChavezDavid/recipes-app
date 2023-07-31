package com.carolina.recipesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carolina.recipesapp.ui.screens.FavoritesScreen
import com.carolina.recipesapp.ui.screens.HomeScreen
import com.carolina.recipesapp.ui.screens.LocationScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItems.Screen1.route) {
        composable(NavigationItems.Screen1.route) {
            HomeScreen()
        }
        composable(NavigationItems.Screen2.route) {
            FavoritesScreen()
        }
        composable(NavigationItems.Screen3.route) {
            LocationScreen()
        }
    }
}
