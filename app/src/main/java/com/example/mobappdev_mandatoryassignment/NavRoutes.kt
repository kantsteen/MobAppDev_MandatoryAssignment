package com.example.mobappdev_mandatoryassignment

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Login : NavRoutes("login")
    data object Register : NavRoutes("register")
    data object Details : NavRoutes("details")
    data object Profile : NavRoutes("profile")
    data object AddItems : NavRoutes("addItems")
    data object UserItems : NavRoutes("userItems")

}