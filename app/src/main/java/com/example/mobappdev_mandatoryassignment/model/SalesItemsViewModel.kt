package com.example.mobappdev_mandatoryassignment.model

import android.R
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.mobappdev_mandatoryassignment.repository.SalesItemsRepository

class SalesItemsViewModel : ViewModel() {
    private val repository = SalesItemsRepository()
    val salesItems: State<List<SalesItem>> = repository.salesItems
    val errorMessage: State<String> = repository.errorMessage
    val isLoadingSalesItem: State<Boolean> = repository.isLoadingBooks


    fun getSalesItems() {
        repository.getSalesItems()
    }

    // TODO()
}