package com.api.wmall.response

sealed class Item {
  class Title(val text: String) : Item()
  class Product(val text: String, val imageUrl: String) : Item()
}
