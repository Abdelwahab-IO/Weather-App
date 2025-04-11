package com.example.data.weather.remote

import com.example.data.BuildConfig
import com.example.data.weather.remote.response.ForecastResponse
import com.example.data.weather.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") key: String = BuildConfig.WEATHER_API_KEY
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String = "cairo",
        @Query("exclude") exc: String = "hourly,minutely",
        @Query("days") days: Int = 8,
        @Query("units") units: String = "metric",
        @Query("appid") key: String = BuildConfig.WEATHER_API_KEY
    ): ForecastResponse
}