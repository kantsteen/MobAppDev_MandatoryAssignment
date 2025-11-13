package com.example.mobappdev_mandatoryassignment.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesItemAdd(
    modifier: Modifier = Modifier,
    addSalesItem: (SalesItem) -> Unit = {},
    onNavigateBack: () -> Unit = {}
){
    var description by remember { mutableStateOf("") }
    var priceStr by remember { mutableStateOf("") }
    var sellerEmail by remember { mutableStateOf("") }
    var sellerPhone by remember { mutableStateOf("") }
    var time by remember { mutableStateOf(LocalDateTime.now()) }
//    var pictureUrl by remember { mutableStateOf("") }

}