package com.api.consumer.feature.widget

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.api.consumer.R
import com.api.consumer.R.layout
import com.api.consumer.feature.Item
import com.api.consumer.feature.Item.Product
import com.api.consumer.feature.Item.Title
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_title.view.*

const val VIEW_TYPE_TITLE = 0
const val VIEW_TYPE_PRODUCT = 1

class WidgetListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Item> = listOf()
        set(value) {
            field = value
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
                LayoutInflater.from(parent.context).inflate(layout.item_title, parent, false)
            )
            VIEW_TYPE_PRODUCT -> ProductViewHolder(
                LayoutInflater.from(parent.context).inflate(layout.item_product, parent, false)
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
                holder.itemView.setOnClickListener { onClickListener.onCategoryClicked() }
                (holder as ProductViewHolder).bindTo(items[position] as Product)
            }
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.tvTitle
        fun bindTo(title: Title) {
            tvTitle.text = title.title
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategory = itemView.tvCategory
        private val ivProduct = itemView.ivProduct
        private val tvCount = itemView.tvCount

        fun bindTo(product: Product) {
            tvCategory.text = product.category
            tvCount.text = product.count.toString()
            Picasso.get().load(product.imageUrl).placeholder(R.drawable.ic_launcher_background).into(
                ivProduct
            )
        }
    }

    interface OnClickListener {
        fun onCategoryClicked()
    }
}

