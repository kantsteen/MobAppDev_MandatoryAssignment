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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobappdev_mandatoryassignment.model.SalesItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemAdd(
    modifier: Modifier = Modifier,
    addSalesItem: (SalesItem) -> Unit = {},
    onNavigateBack: () -> Unit = {},
    userMail: String = "",
) {
    var description by remember { mutableStateOf("") }
    var priceStr by remember { mutableStateOf("") }
    var sellerPhone by remember { mutableStateOf("") }
    var descriptionIsError by remember { mutableStateOf(false) }
    var priceIsError by remember { mutableStateOf(false) }
//    var pictureUrl by remember { mutableStateOf("") }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add salesItem") })
        }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            val orientation = LocalConfiguration.current.orientation
            val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
            if (isPortrait) {
                OutlinedTextField(
                    onValueChange = { description = it },
                    value = description,
                    isError = descriptionIsError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Title") })
                OutlinedTextField(
                    onValueChange = { priceStr = it },
                    value = priceStr,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = priceIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Price") })
                OutlinedTextField(
                    onValueChange = { sellerPhone = it },
                    value = sellerPhone,
                    // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "SellerPhone") })
            } else {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        onValueChange = { description = it },
                        value = description,
                        isError = descriptionIsError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = modifier.fillMaxWidth(1f),
                        label = { Text("Description") })
                    OutlinedTextField(
                        onValueChange = { priceStr = it },
                        value = priceStr,
                        // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = priceIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Price") })
                    OutlinedTextField(
                        onValueChange = { sellerPhone = it },
                        value = sellerPhone,
                        // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "SellerPhone") })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onNavigateBack() }) {
                    Text("Cancel")
                }
                Button(onClick = {
                    if (description.isEmpty()) {
                        descriptionIsError = true
                        return@Button
                    }
                    if (priceStr.isEmpty()) {
                        priceIsError = true
                        return@Button
                    }
                    val price = priceStr.toDoubleOrNull()
                    if (price == null) {
                        priceIsError = true
                        return@Button
                    }
                    val salesItem = SalesItem(
                        description = description,
                        price = priceStr.toInt(),
                        time = (System.currentTimeMillis() / 1000),
                        id = 0,
                        sellerEmail = userMail,
                        sellerPhone = sellerPhone,
                    )
                    addSalesItem(salesItem)
                    onNavigateBack()
                }) {
                    Text("Add")
                }
            }
        }
    }
}

@Preview
@Composable
fun SalesItemAddPreview() {
    SalesItemAdd()
}