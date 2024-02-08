package com.example.various

import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {
    @GET("products/1")
    suspend fun first(): Product

    @GET("products/{id}")
    suspend fun find(@Path("id") id: Int): Product
}

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String
)