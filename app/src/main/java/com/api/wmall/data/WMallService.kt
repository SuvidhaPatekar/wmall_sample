package com.api.wmall.data

import com.api.wmall.response.Response
import io.reactivex.Single
import retrofit2.http.GET

interface WMallService {
  @GET("widgets")
  fun getWidgets(): Single<Response>
}
