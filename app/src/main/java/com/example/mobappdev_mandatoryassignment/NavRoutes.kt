package com.example.mobappdev_mandatoryassignment

sealed class NavRoutes(val route: String) {
    data object Login : NavRoutes("login")
    data object SalesItemDetails : NavRoutes("details")
    data object Profile : NavRoutes("profile")
    data object SalesItemAdd : NavRoutes("addItems")
    data object SalesItemList : NavRoutes("list")
}