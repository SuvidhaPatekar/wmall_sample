package com.api.consumer.response

import com.google.gson.annotations.SerializedName

data class Widget(
    val title: String,
    @field:SerializedName("items")
    val products: List<Product>
)
