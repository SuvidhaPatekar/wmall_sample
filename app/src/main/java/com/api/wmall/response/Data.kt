package com.api.wmall.response

data class Data(
	val id: Int? = null,
	val category: String? = null,
	val items: List<CategoryItem?>? = null,
	val slug: String? = null
)
