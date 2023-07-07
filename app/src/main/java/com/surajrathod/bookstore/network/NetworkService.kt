package com.surajrathod.bookstore.network

import com.surajrathod.bookstore.model.Products
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("products")
    suspend fun loadProducts() : Products

}