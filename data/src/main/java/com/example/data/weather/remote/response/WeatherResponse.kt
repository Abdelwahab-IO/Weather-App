package com.example.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") val coord: CoordResponse,
    @SerializedName("weather") val weather: List<InternalWeatherResponse>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainResponse,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val cityId: Int,
    @SerializedName("name") val cityName: String,
    @SerializedName("cod") val cod: Int
)