package com.example.mobappdev_mandatoryassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobappdev_mandatoryassignment.model.AuthViewModel
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import com.example.mobappdev_mandatoryassignment.model.SalesItemsViewModel
import com.example.mobappdev_mandatoryassignment.screens.AuthScreen
import com.example.mobappdev_mandatoryassignment.screens.SalesItemAdd
import com.example.mobappdev_mandatoryassignment.screens.SalesItemDetails
import com.example.mobappdev_mandatoryassignment.screens.SalesItemList
import com.example.mobappdev_mandatoryassignment.ui.theme.MobAppDev_MandatoryAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobAppDev_MandatoryAssignmentTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                SalesItem("BMW", 100000)
//                SalesItems()
                MainScreen()


            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    salesItemsViewModel: SalesItemsViewModel = SalesItemsViewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val navController = rememberNavController()
    val salesItems = salesItemsViewModel.salesItems.value
    val errorMessage = salesItemsViewModel.errorMessage.value

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SalesItemList.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(NavRoutes.SalesItemList.route) {
            SalesItemList(
                modifier = modifier,
                salesItems = salesItems,
                errorMessage = errorMessage,
                onSalesItemSelected = { salesItem ->
                    navController.navigate((NavRoutes.SalesItemDetails.route) + "/${salesItem.id}")
                },
                onSalesItemDeleted = { salesItem -> salesItemsViewModel.removeSalesItem(salesItem) },
                onAdd = { navController.navigate(NavRoutes.SalesItemAdd.route) },
                sortByTitle = { salesItemsViewModel.sortSalesItemsByDescription(ascending = it) },
                sortByPrice = { salesItemsViewModel.sortSalesItemsByPrice(ascending = it) },
                filterByTitle = { salesItemsViewModel.filterSalesItemsByDescription(it) },
                filterByPrice = { salesItemsViewModel.filterSalesItemsByPrice(minPrice = it, maxPrice = it) },
                onSalesItemsReload = { salesItemsViewModel.getSalesItems() },
                salesItemsLoading = salesItemsViewModel.isLoadingSalesItem.value
            )
        }
        composable(
            NavRoutes.SalesItemDetails.route + "/{salesItemId}",
            arguments = listOf(navArgument("salesItemId") { type = NavType.IntType })
        ) { backstackEntry ->
            val salesItemId = backstackEntry.arguments?.getInt("salesItemId")
            val salesItem = salesItems.find { it.id == salesItemId } ?: SalesItem(
                description = "Not found",
                price = 0.0,
                sellerMail = "",
                sellerPhone = "",
                time = 0,
//                pictureUrl = ""
            )
            SalesItemDetails(
                salesItem = salesItem,
                modifier = modifier,
                onNavigateBack = { navController.popBackStack() },
                onUpdate = { id: Int, salesItem: SalesItem ->
                    salesItemsViewModel.updateSalesItem(
                        id,
                        salesItem
                    )
                }
            )
        }
        composable(NavRoutes.SalesItemAdd.route) {
            SalesItemAdd(
                modifier = modifier,
                addSalesItem = { salesItem -> salesItemsViewModel.addSalesItem(salesItem) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(NavRoutes.Authentication.route) {
            AuthScreen(
                user = authViewModel.user,
                message = authViewModel.message,
                signIn = { email, password -> authViewModel.signIn(email, password) },
                register = authViewModel::register,
                navigateToNextScreen = { navController.navigate(NavRoutes.SalesItemList.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SalesItemPreview(){
    MobAppDev_MandatoryAssignmentTheme {
        MainScreen()
    }
}
//{ salesItem -> navController.navigate(NavRoutes.Details.route + "/@{salesItem.id}") },

//@Composable
//fun SalesItem(description: String, price: Int, modifier: Modifier = Modifier) {
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
//    ) {
//        Column(modifier) {
//
//            val image = painterResource(R.drawable.bmw_blue_sample)
//            Image(
//                painter = image,
//                contentDescription = null
//            )
//            Text(
//                "$description \n$price DKK",
//                modifier = modifier
//            )
//        }
//    }
//}
//
//@Composable
//fun SalesItems(
//    modifier: Modifier = Modifier,
//    descriptions: List<String> = List(50) { "$it" }
//) {
//    LazyColumn(Modifier.padding(vertical = 4.dp)) {
//        items(items = descriptions) { description ->
//            Row(
//                modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.Top){
//                SalesItem(description = "BMW", 1000)
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun SalesItemsPreview() {
//    MobAppDev_MandatoryAssignmentTheme {
//        SalesItems()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SalesItemPreview() {
//    Column(modifier = Modifier) {// can maybe be deleted
//        Row(modifier = Modifier) {
//            SalesItem("BMW", 100000)
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobAppDev_MandatoryAssignmentTheme {
//        Greeting("Android")
//    }
//}