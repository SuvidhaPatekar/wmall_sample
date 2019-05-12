package com.api.wmall.response

data class Products(
	val image: Image,
	val id: Int,
	val category: String,
	val slug: String,
	val itemCount: Int
)
