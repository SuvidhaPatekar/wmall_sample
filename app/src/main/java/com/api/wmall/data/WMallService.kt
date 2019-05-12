package com.api.wmall.data

import com.api.wmall.response.ResponseCategory
import com.api.wmall.response.ResponseWidget
import io.reactivex.Single
import retrofit2.http.GET

interface WMallService {
  @GET("widgets")
  fun getWidgets(): Single<ResponseWidget>

  @GET("category/13")
  fun getCategoryDetails(): Single<ResponseCategory>
}
