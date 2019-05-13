package com.api.wmall.response

import com.google.gson.annotations.SerializedName

data class Category(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
