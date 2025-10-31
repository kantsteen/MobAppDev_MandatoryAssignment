package com.example.mobappdev_mandatoryassignment.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SalesItemsRepository {
    private val baseUrl = "https://anbo-salesitems.azurewebsites.net/api/SalesItems"
    private val salesItemService: SalesItemService
    val salesItems: MutableState<List<SalesItem>> = mutableStateOf(listOf())
    val isLoadingBooks = mutableStateOf(false)
    val errorMessage = mutableStateOf("")


    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        salesItemService = build.create(SalesItemService::class.java)
        getSalesItems()
    }

    fun getSalesItems() {
        isLoadingBooks.value = true
        salesItemService.getAllSalesItems().enqueue(object : Callback<List<SalesItem>> {
            override fun onResponse(
                call: Call<List<SalesItem>>,
                response: Response<List<SalesItem>>
            ) {
                isLoadingBooks.value = false
                if (response.isSuccessful) {
                    val salesItemList: List<SalesItem>? = response.body()
                    salesItems.value = salesItemList ?: emptyList()
                    errorMessage.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessage.value = message
                    Log.e("APPLE", message)
                }
            }

            override fun onFailure(call: Call<List<SalesItem>?>, t: Throwable) {
                isLoadingBooks.value = false
                val message = t.message ?: "No connection to backend"
                errorMessage.value = message
                Log.e("APPLE", message)
            }
        })
    }

    fun add() {
        TODO("Implement add")
    }

    fun deleteSalesItem(id: Int) {
        Log.d("APPLE", "Delete: $id")
        salesItemService.deleteSalesItem(id).enqueue(object : Callback<SalesItem> {
            override fun onResponse(call: Call<SalesItem?>, response: Response<SalesItem?>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Delete: " + response.body())
                    errorMessage.value = ""
                    getSalesItems()
                } else {
                    val message = response.code().toString() + " " + response.body()
                    errorMessage.value = message
                    Log.e("APPLE", "Not Deleted")
                }
            }

            override fun onFailure(call: Call<SalesItem?>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessage.value = message
                Log.e("APPLE", "Update $message")
            }
        })
    }
    }

    fun update() {
        TODO("Implement update")
    }

    fun sortSalesItemsByTitle() {
        TODO("Implement sortSalesItemsByTitle")
    }

    fun sortSalesItemsByPrice() {
        TODO("Implement sortSalesItemsByPrice")
    }

    fun filterSalesItemsByPrice() {
        TODO("Implement filterSalesItemsByPrice")
    }














