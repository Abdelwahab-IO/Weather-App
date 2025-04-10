package com.example.data.weather.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
class WeatherClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // This will log request and response bodies
    }

    // Create an OkHttpClient with the interceptor
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
   private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(WeatherApi::class.java)
}