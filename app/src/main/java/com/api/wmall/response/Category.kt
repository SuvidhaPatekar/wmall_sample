package com.api.wmall.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(

  @field:SerializedName("image")
  val image: Image,

  @field:SerializedName("price")
  val price: Int,

  @field:SerializedName("title")
  val title: String,

  @field:SerializedName("slug")
  val slug: String
) : Serializable
