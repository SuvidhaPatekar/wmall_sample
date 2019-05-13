package com.api.wmall.response

import com.google.gson.annotations.SerializedName

data class Widget(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("items")
	val products: List<Product>
)
