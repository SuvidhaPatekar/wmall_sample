package com.api.wmall.response

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("items")
	val categories: List<Category>,

	@field:SerializedName("slug")
	val slug: String? = null
)
