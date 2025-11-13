package com.example.mobappdev_mandatoryassignment.screens

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
        modifier = modifier.fillMaxSize(), topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("SalesItem Details") })

        }) { innerPadding ->
        // TODO show error message
        Column(modifier = modifier.padding(innerPadding)) {
            // TODO layout for landscape
            // TODO add and details are very similar
            TextField(
                onValueChange = { description = it },
                value = description,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descriptiom") })
            TextField(
                onValueChange = { priceStr = it },
                value = priceStr,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Price") })
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onNavigateBack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    // TODO validation
                    val data = SalesItem(
                        description = description,
                        price = priceStr.toDouble(),
                        id = TODO(),
                        sellerMail = TODO(),
                        sellerPhone = TODO(),
                        time = TODO(),
//                        pictureUrl = TODO()
                    )
                    onUpdate(salesItem.id, data)
                    onNavigateBack()
                }) {
                    Text("Update")
                }
            }
        }
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
            time = LocalDateTime.of(2025, 11, 13, 13, 30),
//            pictureUrl = "pictureURL"
        )
    )
}