package com.api.wmall.data

import com.api.wmall.feature.Item
import com.api.wmall.response.Category
import io.reactivex.Single

object WMallDataManager {
  private val wMallService = RetrofitProvider.getRetrofit()
      .create(
          WMallService::class.java
      )

  fun getWidgets(): Single<List<Item>> {
    return wMallService.getWidgets()
        .map { it.widgets }
        .flattenAsObservable { it }
        .map {
          listOf(Item.Title(it.title)) +
              it.items.map { Item.Product(it.slug, it.image.mobile!!) }
        }
        .singleOrError()
  }

  fun getCategoryDetails(): Single<List<Category>> {
    return wMallService.getCategoryDetails().map {
        it.data?.categories
    }
  }
}
