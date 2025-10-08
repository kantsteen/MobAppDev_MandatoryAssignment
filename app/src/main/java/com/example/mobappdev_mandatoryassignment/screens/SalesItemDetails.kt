package com.example.mobappdev_mandatoryassignment.screens

import android.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemDetails(
    salesItem: SalesItem,
    modifier: Modifier = Modifier,
    onUpdate: (Int, SalesItem) -> Unit = { id: Int, data: SalesItem -> },
    onNavigateBack: () -> Unit = {}
) {
    var description by remember { mutableStateOf(salesItem.description) }
    var priceStr by remember { mutableStateOf(salesItem.price.toString()) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ContentColor = MaterialTheme.colorScheme.primary
                )
            ),
            description = { Text("SalesItem details") }
        }) {

    }


}


@Preview
@Composable
fun SalesItemDetailsPreview() {
    SalesItemDetails(
        salesItem = SalesItem(
            id = 1,
            description = "Bicycle",
            price = 1000.0,
            sellerMail = "Disney@Disney.com",
            sellerPhone = "12345678",
            time = LocalDateTime.now(),
            pictureUrl = "pictureURL"
        )
    )
}