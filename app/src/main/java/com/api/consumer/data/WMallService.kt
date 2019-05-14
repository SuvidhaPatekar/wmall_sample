package com.api.consumer.data

import com.api.consumer.response.ResponseCategory
import com.api.consumer.response.ResponseWidget
import io.reactivex.Single
import retrofit2.http.GET

interface WMallService {
  @GET("widgets")
  fun getWidgets(): Single<ResponseWidget>

  @GET("category/13")
  fun getCategoryDetails(): Single<ResponseCategory>
}
