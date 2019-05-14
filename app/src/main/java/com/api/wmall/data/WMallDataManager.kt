package com.api.wmall.data

import com.api.wmall.feature.Item
import com.api.wmall.response.Category
import io.reactivex.Observable
import io.reactivex.Single

object WMallDataManager {
  private val wMallService = RetrofitProvider.getRetrofit()
      .create(
          WMallService::class.java
      )

  fun getWidgets(): Single<MutableList<Item>> {
    return wMallService.getWidgets()
        .map { it.widgets }
        .flattenAsObservable { it }
        .map { widget ->
          listOf(Item.Title(widget.title)) +
              widget.products.map{ Item.Product(it.itemCount, it.image.mobile, it.category, it.slug) }
        }
        .flatMapIterable { it }
        .toList()
  }

  fun getCategoryDetails(): Single<List<Category>> {
    return wMallService.getCategoryDetails().map {
        it.data?.categories
    }
  }
}
