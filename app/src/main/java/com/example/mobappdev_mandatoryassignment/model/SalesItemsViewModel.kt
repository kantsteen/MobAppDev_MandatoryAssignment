package com.example.mobappdev_mandatoryassignment.model

import android.R
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.mobappdev_mandatoryassignment.repository.SalesItemService
import com.example.mobappdev_mandatoryassignment.repository.SalesItemsRepository

class SalesItemsViewModel : ViewModel() {
    private val repository = SalesItemsRepository()
    val salesItems: State<List<SalesItem>> = repository.salesItems
    val errorMessage: State<String> = repository.errorMessage
    val isLoadingSalesItem: State<Boolean> = repository.isLoadingBooks


    fun getSalesItems() {
        repository.getSalesItems()
    }

    fun removeSalesItem(salesItem: SalesItem) {
        repository.deleteSalesItem(salesItem.id)
    }

    fun updateSalesItem(salesItemId: Int, salesItem: SalesItem) {
        repository.updateSalesItem(salesItemId, salesItem)
    }

    fun addSalesItem(salesItem: SalesItem) {
        repository.addSalesItem(salesItem)
    }

    fun sortSalesItemsByDescription(ascending: Boolean) {
        repository.sortSalesItemsByDescription(ascending)
    }

    fun sortSalesItemsByPrice(ascending: Boolean) {
        repository.sortSalesItemsByPrice(ascending)
    }

    fun filterSalesItemsByDescription(description: String) {
        repository.filterSalesItemsByDescription(description)
    }

    fun filterSalesItemsByPrice(minPrice: Double, maxPrice: Double) {
        repository.filterSalesItemsByPrice(minPrice, maxPrice)
    }
}




    // TODO()
