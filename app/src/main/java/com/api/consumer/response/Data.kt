package com.api.consumer.response

import com.google.gson.annotations.SerializedName

data class Data(
    val id: Int? = null,
    val category: String? = null,
    val slug: String? = null,
    @field:SerializedName("items")
    val categories: List<Category>
)
