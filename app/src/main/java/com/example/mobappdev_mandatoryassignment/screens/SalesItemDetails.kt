package com.example.mobappdev_mandatoryassignment.screens

import android.R
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import com.example.mobappdev_mandatoryassignment.model.SalesItemsViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemDetails(
    salesItem: SalesItem,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onItemDeleted: (salesItem: SalesItem) -> Unit = {},
) {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    var description by remember { mutableStateOf(salesItem.description) }
    var priceStr by remember { mutableStateOf(salesItem.price.toString()) }
    var time by remember { mutableStateOf(salesItem.time.toString()) }
    var sellerMail by remember { mutableStateOf(salesItem.sellerMail) }
    var sellerPhone by remember { mutableStateOf(salesItem.sellerPhone) }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("SalesItem Details") })

        }) { innerPadding ->
        // TODO show error message
        Column(modifier = modifier.padding(innerPadding)) {
            val orientation = LocalConfiguration.current.orientation
            val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
            if (isPortrait){
            // TODO add and details are very similar
            OutlinedTextField(
                onValueChange = { description = it },
                value = description,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") })
            OutlinedTextField(
                onValueChange = { priceStr = it },
                value = priceStr,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Price") })
            OutlinedTextField(
                onValueChange = { time = it },
                value = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Date") }) // TODO convert from unix to LocalDateTime
            OutlinedTextField(
                onValueChange = { sellerMail = it },
                value = sellerMail,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Seller Mail") })
            OutlinedTextField(
                onValueChange = { sellerPhone = it },
                value = sellerPhone,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Seller Phone") })

                Button(onClick = { onNavigateBack() }) {
                Text("Back")
            }}

            else {
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    onValueChange = { description = it },
                    value = description,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Description") })
                OutlinedTextField(
                    onValueChange = { priceStr = it },
                    value = priceStr,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Price") })
                OutlinedTextField(
                    onValueChange = { time = it },
                    value = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Date") }) // TODO convert from unix to LocalDateTime
                OutlinedTextField(
                    onValueChange = { sellerMail = it },
                    value = sellerMail,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Seller Mail") })
                OutlinedTextField(
                    onValueChange = { sellerPhone = it },
                    value = sellerPhone,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Seller Phone") })
            }
                Button(onClick = { onNavigateBack() }) {
                    Text("Back")
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun SalesItemDetailsPreview() {
//    SalesItemDetails(
//        salesItem = SalesItem(
//            id = 1,
//            description = "Bicycle",
//            price = 1000.0,
//            sellerMail = "Disney@Disney.com",
//            sellerPhone = "12345678",
//            time = LocalDateTime.now(),
////            pictureUrl = "pictureURL"
//        )
//    )
//}