package com.example.mobappdev_mandatoryassignment.repository

import com.example.mobappdev_mandatoryassignment.model.SalesItem
import retrofit2.Call

import retrofit2.http.*

interface SalesItemService {
    @GET("salesItem")
    fun getAllSalesItems(): Call<List<SalesItem>>

    @DELETE("salesItem/{salesItemId}")
    fun deleteSalesItem(@Path("salesItemId") id: Int): Call<SalesItem>

    // TODO
    // rest of interface


















}