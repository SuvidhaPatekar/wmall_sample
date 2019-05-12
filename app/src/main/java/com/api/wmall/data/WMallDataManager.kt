package com.api.wmall.data

import com.api.wmall.response.Response
import io.reactivex.Single

class WMallDataManager {
  private val wMallService = RetrofitProvider.getRetrofit()
      .create(
          WMallService::class.java
      )

  fun getWidgets(): Single<Response> {
    return wMallService.getWidgets()
  }
}
