package com.carolina.recipesapp.navigation

sealed class AppScreens(
    val route: String,
) {
    object HomeScree : AppScreens("home_screen")
    object DetailScreen : AppScreens("detail_screen")
}
