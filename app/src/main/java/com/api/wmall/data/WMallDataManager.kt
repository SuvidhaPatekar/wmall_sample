package com.api.wmall.data

import com.api.wmall.response.Item
import com.api.wmall.response.ResponseCategory
import io.reactivex.Single

object WMallDataManager {
  private val wMallService = RetrofitProvider.getRetrofit()
      .create(
          WMallService::class.java
      )

  fun getWidgets(): Single<List<Item>> {
    return wMallService.getWidgets()
  }

  fun getCategoryDetails(): Single<ResponseCategory> {
    return wMallService.getCategoryDetails()
  }
}
