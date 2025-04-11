package com.example.data.weather.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherClient(apiKey:String) {
    private val okHttpClient = OkHttpClient.Builder() .addInterceptor { chain ->
        val originalRequest: Request = chain.request()
        val modifiedUrl = originalRequest.url().newBuilder()
            .addQueryParameter("appid", apiKey)
            .build()
        val newRequest: Request = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()
        chain.proceed(newRequest)
    }
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: WeatherApi = retrofit.create(WeatherApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}