package com.demo.inputmarketplacekotlin.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASIC_AUTH =
        "Basic NzU2Njk5NzcwOTpFdXRlbklWaXl5MlM5b2lWVDJDb2h4RkVuNXp4aFAwSFo5aDN4TmI1eVYwPQ==";

    private val okHttpClient = OkHttpClient.Builder().apply {

    }
        .addInterceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
            requestBuilder.addHeader("Authorization", BASIC_AUTH)
            val request1 = requestBuilder.build()
            chain.proceed(request1)
        }
        .build()

    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiSettings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ApiInterface::class.java)
    }
}