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

  fun getWidgets(): Observable<List<Item>> {
    return wMallService.getWidgets()
        .map { it.widgets }
        .flattenAsObservable { it }
        .map { widget ->
          listOf(Item.Title(widget.title)) +
              widget.products.map{ Item.Product(it.slug, it.image.mobile, it.category) }
        }

  }

  fun getCategoryDetails(): Single<List<Category>> {
    return wMallService.getCategoryDetails().map {
        it.data?.categories
    }
  }
}
