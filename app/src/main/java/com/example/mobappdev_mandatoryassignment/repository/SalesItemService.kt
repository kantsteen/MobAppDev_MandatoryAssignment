package com.example.mobappdev_mandatoryassignment.repository

import com.example.mobappdev_mandatoryassignment.model.SalesItem
import retrofit2.Call

import retrofit2.http.*

interface SalesItemService {
    @GET("SalesItems")
    fun getAllSalesItems(): Call<List<SalesItem>>

    @DELETE("SalesItems/{salesItemId}")
    fun deleteSalesItem(@Path("salesItemId") id: Int): Call<SalesItem>

    @PUT("SalesItems/{salesItemId}")
    fun updateSalesItem(@Path("salesItemId") id: Int, @Body salesItem: SalesItem): Call<SalesItem>

    @POST("SalesItems")
    fun addSalesItem(@Body salesItem: SalesItem): Call<SalesItem>
}