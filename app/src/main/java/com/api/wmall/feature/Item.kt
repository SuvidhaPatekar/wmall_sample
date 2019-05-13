package com.api.wmall.feature

sealed class Item {
  class Title(val title: String) : Item()
  class Product(val slug: String,val imageUrl: String) : Item()
}
