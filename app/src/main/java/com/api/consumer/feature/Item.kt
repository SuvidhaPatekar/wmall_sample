package com.api.consumer.feature

sealed class Item {
  class Title(val title: String) : Item()
  class Product(val count: Int,val imageUrl: String, val category: String, val slug: String) : Item()
}
