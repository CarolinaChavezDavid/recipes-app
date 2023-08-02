package com.carolina.recipesapp.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun currentRoute(navController: NavController): String? {
    val currentScreen by navController.currentBackStackEntryAsState()
    return currentScreen?.destination?.route
}

@Composable
fun RecipesBottomNavigation(navController: NavController, menuItems: List<AppScreens>) {
    BottomNavigation {
        val currentRoute = currentRoute(navController = navController)

    }
}
