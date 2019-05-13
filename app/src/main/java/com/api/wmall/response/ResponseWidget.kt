package com.api.wmall. response

import com.google.gson.annotations.SerializedName

data class ResponseWidget(

	@field:SerializedName("widgets")
	val widgets: List<Widget>? = null
)
