package com.api.wmall.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.api.wmall.R
import com.api.wmall.feature.Item.Product
import com.api.wmall.feature.Item.Title
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.ivProduct
import kotlinx.android.synthetic.main.item_product.view.tvSlug
import kotlinx.android.synthetic.main.item_product.view.tvTime
import kotlinx.android.synthetic.main.item_title.view.tvTitle

class WMallAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: List<Item> = listOf()
  val VIEW_TYPE_TITLE = 0
  val VIEW_TYPE_PRODUCT = 1

  fun setItems(items: List<Item>){
    this.items = items
    notifyDataSetChanged()
  }

  override fun getItemViewType(position: Int): Int {
    return when (items[position]) {
      is Item.Title -> VIEW_TYPE_TITLE
      is Item.Product -> VIEW_TYPE_PRODUCT
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    return when (viewType) {
      VIEW_TYPE_TITLE -> TitleViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
      )
      VIEW_TYPE_PRODUCT -> ProductViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
      )
      else -> throw IllegalStateException()
    }
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    when (items[position]) {
      is Item.Title -> {
        (holder as TitleViewHolder).bindTo(items[position] as Title)
      }
      is Item.Product -> {
        (holder as ProductViewHolder).bindTo(items[position] as Product)
      }
    }
  }

  class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.tvTitle
    fun bindTo(title : Title) {
       tvTitle.text = title.title
    }
  }

  class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvSlug = itemView.tvSlug
    private val tvTime = itemView.tvTime
    private val ivProduct = itemView.ivProduct

    fun bindTo(product: Product) {
       tvTime.text = product.slug
       tvSlug.text = product.slug
    }
  }
}

