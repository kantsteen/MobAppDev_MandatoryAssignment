package com.example.mobappdev_mandatoryassignment

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Login : NavRoutes("login")
    data object Register : NavRoutes("register")
    data object SalesItemDetails : NavRoutes("details")
    data object Profile : NavRoutes("profile")
    data object SalesItemAdd : NavRoutes("addItems")
    data object UserItems : NavRoutes("userItems")
    data object SalesItemList : NavRoutes("list")
}