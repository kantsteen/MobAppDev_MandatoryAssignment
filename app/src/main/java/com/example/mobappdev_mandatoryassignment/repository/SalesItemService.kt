package com.example.mobappdev_mandatoryassignment.repository

import com.example.mobappdev_mandatoryassignment.model.SalesItem
import retrofit2.Call

import retrofit2.http.*

interface SalesItemService {
    @GET("salesItems")
    fun getAllSalesItems(): Call<List<SalesItem>>

    @DELETE("salesItems/{salesItemId}")
    fun deleteSalesItem(@Path("salesItemId") id: Int): Call<SalesItem>

    @PUT("salesItems/{salesItemId}")
    fun updateSalesItem(@Path("salesItemId") id: Int, @Body salesItem: SalesItem): Call<SalesItem>

    @POST("salesItems")
    fun createSalesItem(@Body salesItem: SalesItem): Call<SalesItem>
    // TODO?
    // rest of interface


















}