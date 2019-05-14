package com.api.consumer.data

import com.api.consumer.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
  private fun getBaseUrl() = "https://demo0263531.mockable.io/"

  internal fun getRetrofit() = Retrofit.Builder()
      .baseUrl(getBaseUrl())
      .client(getOkHttpClient())
      .addConverterFactory(GsonConverterFactory.create(getGson()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()

  private fun getOkHttpClient() = OkHttpClient.Builder().apply {
    if (BuildConfig.DEBUG) {
      addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
      })
    }
  }.build()

  private fun getGson() = GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
}

