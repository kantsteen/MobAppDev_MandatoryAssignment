package com.example.mobappdev_mandatoryassignment.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobappdev_mandatoryassignment.components.AppTopBar
import com.example.mobappdev_mandatoryassignment.model.AuthViewModel
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemList(
    salesItems: List<SalesItem>,
    errorMessage: String,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel? = null,
    onSalesItemSelected: (SalesItem) -> Unit = {},
    onSalesItemDeleted: (SalesItem) -> Unit = {},
    onSalesItemsReload: () -> Unit = {},
    salesItemsLoading: Boolean = false,
    onAdd: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    sortByTitle: (up: Boolean) -> Unit = {},
    sortByPrice: (up: Boolean) -> Unit = {},
    filterByTitle: (String) -> Unit = {},
    filterByPrice: (Double) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            AppTopBar(
                title = "Sales Items",
                authViewModel = authViewModel,
                onLoginClick = onLoginClick,
                onLogoutClick = onLoginClick,  // Navigate to login after logout
                onProfileClick = onProfileClick
            )
//                title = { Text("Sales item list")
//                    /*val navController = rememberNavController()
//                        val navBackStackEntry by navController.currentBackStackEntryAsState()
//                        val currentDestination = navBackStackEntry?.destination?.route
//                        val title = when(currentDestination) {
//                            "profile" -> "Profile"
//                            "friendslist" -> "Friends"
//                            else -> null
//                        }
//                        title?.let { Text(it) }*/}

        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { onAdd() }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }) { innerPadding ->
        SalesItemListPanel(
            salesItems = salesItems,
            modifier = Modifier.padding(innerPadding),
            errorMessage = errorMessage,
            sortByTitle = sortByTitle,
            sortByPrice = sortByPrice,
            onSalesItemsReload = onSalesItemsReload,
            salesItemsLoading = salesItemsLoading,
            onSalesItemSelected = onSalesItemSelected,
            onSalesItemDeleted = onSalesItemDeleted,
            onFilterByTitle = filterByTitle,
            onFilterByPrice = filterByPrice
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SalesItemListPanel(
    salesItems: List<SalesItem>,
    modifier: Modifier = Modifier,
    errorMessage: String,
    sortByTitle: (up: Boolean) -> Unit,
    sortByPrice: (up: Boolean) -> Unit,
    onSalesItemsReload: () -> Unit = {},
    salesItemsLoading: Boolean = false,
    onSalesItemSelected: (SalesItem) -> Unit,
    onSalesItemDeleted: (SalesItem) -> Unit,
    onFilterByTitle: (String) -> Unit,
    onFilterByPrice: (Double) -> Unit
) {
    Column(modifier = modifier.padding(8.dp)) {
        if (errorMessage.isNotEmpty()) {
            Text("Problem: $errorMessage")
        }
        val titleUp = "title \u2191"
        val titleDown = "title \u2193"
        val priceUp = "price \u2191"
        val priceDown = "price \u2193"
        var sortTitleAscending by remember { mutableStateOf(true) }
        var sortPriceAscending by remember { mutableStateOf(true) }
        var titleFragment by remember { mutableStateOf("") }
        var priceFragment by remember { mutableStateOf("") }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = titleFragment,
                onValueChange = { titleFragment = it },
                label = { Text("Filter by title") },
                modifier = Modifier.weight(1f)
            )
            /*Button(
                onClick = { onFilterByTitle(titleFragment) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Filter")
            }*/
            OutlinedTextField(
                value = priceFragment,
                onValueChange = { priceFragment = it },
                label = { Text("Filter by price") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { onFilterByTitle(titleFragment); onFilterByPrice(priceFragment.toDouble()) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Filter")
            }
        }

        Row {
            OutlinedButton(
                modifier = Modifier.padding(top = 3.dp),
                onClick = {
                    sortByTitle(sortTitleAscending)
                    sortTitleAscending = !sortTitleAscending
                }) {
                Text(text = if (sortTitleAscending) titleDown else titleUp)
            }
            OutlinedButton(
                modifier = Modifier.padding(start = 3.dp, top = 3.dp),
                onClick = {
                    sortByPrice(sortPriceAscending)
                    sortPriceAscending = !sortPriceAscending
                }) {
                Text(text = if (sortPriceAscending) priceDown else priceUp)
            }
        }
        val orientation = LocalConfiguration.current.orientation
        val columns = if (orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2

        PullToRefreshBox(
            isRefreshing = salesItemsLoading,
            onRefresh = { onSalesItemsReload() }
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(columns)) {
                items(salesItems) { salesItem ->
                    SalesItem(
                        salesItem,
                        onSalesItemSelected = onSalesItemSelected,
                        onSalesItemDeleted = onSalesItemDeleted
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SalesItem(
    salesItem: SalesItem,
    modifier: Modifier = Modifier,
    onSalesItemSelected: (SalesItem) -> Unit = {},
    onSalesItemDeleted: (SalesItem) -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize(), onClick = { onSalesItemSelected(salesItem) }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                modifier = Modifier.padding(8.dp),
                text = salesItem.description + " " + salesItem.price.toString()
            )
            // TODO move delete icon to profile page for user's own listed items
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Remove" + salesItem.description,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onSalesItemDeleted(salesItem) }
            )
        }
    }

}

@Preview
@Composable
fun SalesItemListPreview() {
    SalesItemList(
        salesItems = listOf(
            SalesItem(
                id = 1,
                description = "Bicycle",
                price = 1000.00,
                sellerMail = "pizza@mail.com",
                sellerPhone = "12345678",
                time = 0,
//                pictureUrl = "URL"
            ),
            SalesItem(
                id = 2,
                description = "Laptop",
                price = 4000.00,
                sellerMail = "pizza@mail.com",
                sellerPhone = "12345678",
                time = 0,
//                pictureUrl = "URL"
            )
        ),
        errorMessage = "",
        salesItemsLoading = false,
    )
}

