package com.api.wmall.response

import com.google.gson.annotations.SerializedName

data class Product(

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("itemCount")
	val itemCount: Int
)
