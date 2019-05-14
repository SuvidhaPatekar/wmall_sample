package com.api.consumer.response

import java.io.Serializable

data class Image(
    val mobile: String,
    val placeholder: String
) : Serializable
