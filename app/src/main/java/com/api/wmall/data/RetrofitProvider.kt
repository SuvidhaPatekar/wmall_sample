package com.api.wmall.data

import com.api.wmall.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

  private fun getBaseUrl(): String {
    return "https://demo0263531.mockable.io/"
  }

  internal fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  private fun getOkHttpClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      httpClientBuilder.addInterceptor(getOkHttpLoggingInterceptor())
    }
    return httpClientBuilder.build()
  }

  private fun getOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
  }

  private fun getGson(): Gson {
    return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
  }
}
