package com.example.charlestest

import retrofit2.http.GET


interface ProductService {
    @GET("products/1")
    suspend fun first(): Product
}

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String
)