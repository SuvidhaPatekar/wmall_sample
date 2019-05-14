package com.api.wmall.response

import java.io.Serializable

data class Category(
    val image: Image,
    val price: Int,
    val title: String,
    val slug: String
) : Serializable
