package com.api.consumer.response

data class Product(
    val image: Image,
    val id: Int,
    val category: String,
    val slug: String,
    val itemCount: Int
)
