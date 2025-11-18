package com.example.mobappdev_mandatoryassignment.screens

import android.content.res.Configuration
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemDetails(
    salesItem: SalesItem,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {}
) {
    // Convert Unix timestamp to readable date string
    val dateStr = remember(salesItem.time) {
        try {
            salesItem.time?.let {
                if (it > 0) {
                    val date = Date(salesItem.time * 1000) // Convert seconds to milliseconds
                    SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(date)
                } else {
                    "No date"
                }
            }
        } catch (e: Exception) {
            "${e.message}\nInvalid date"
        }
    }

    val orientation = LocalConfiguration.current.orientation
    val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("SalesItem Details") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (isPortrait) {
                // Portrait layout
                OutlinedTextField(
                    value = salesItem.description,
                    onValueChange = { },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Description") }
                )

                OutlinedTextField(
                    value = "${salesItem.price} DKK",
                    onValueChange = { },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Price") }
                )

                OutlinedTextField(
                    value = dateStr ?: "No date",
                    onValueChange = { },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Date Listed") }
                )

                OutlinedTextField(
                    value = salesItem.sellerEmail,
                    onValueChange = { },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Seller Email") }
                )

                OutlinedTextField(
                    value = salesItem.sellerPhone,
                    onValueChange = { },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Seller Phone") }
                )

                Button(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                ) {
                    Text("Back")
                }
            } else {
                // Landscape layout
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = salesItem.description,
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Description") }
                        )

                        OutlinedTextField(
                            value = "${salesItem.price} DKK",
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Price") }
                        )

                        OutlinedTextField(
                            value = dateStr ?: "No date",
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Date Listed") }
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = salesItem.sellerEmail,
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Seller Email") }
                        )

                        OutlinedTextField(
                            value = salesItem.sellerPhone,
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Seller Phone") }
                        )
                    }
                }

                Button(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                ) {
                    Text("Back")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SalesItemDetailsPreview() {
    SalesItemDetails(
        salesItem = SalesItem(
            id = 1,
            description = "Bicycle",
            price = 1000,
            sellerEmail = "Disney@Disney.com",
            sellerPhone = "12345678",
            time = System.currentTimeMillis() / 1000
        )
    )
}