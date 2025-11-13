package com.example.mobappdev_mandatoryassignment

sealed class NavRoutes(val route: String) {
    data object Authentication : NavRoutes("authentication")
    data object SalesItemList : NavRoutes("list")
    data object SalesItemDetails : NavRoutes("details")
    data object Profile : NavRoutes("profile")
    data object SalesItemAdd : NavRoutes("addItems")
}