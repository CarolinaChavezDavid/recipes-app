package com.carolina.recipesapp.navigation

import com.carolina.recipesapp.R

sealed class NavigationItems(
    val destination: String,
    val icon: Int,
    val route: String,
) {
    object Screen1 : NavigationItems("Home", R.drawable.baseline_home_24, "screen_1")
    object Screen2 : NavigationItems("Favorite", R.drawable.baseline_favorite_border_24, "screen_2")
    object Screen3 : NavigationItems("Location", R.drawable.baseline_location_on_24, "screen_3")
}
