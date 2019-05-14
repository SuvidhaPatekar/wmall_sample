package com.api.wmall.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(

	@field:SerializedName("mobile")
	val mobile: String,

	@field:SerializedName("placeholder")
	val placeholder: String
) : Serializable
