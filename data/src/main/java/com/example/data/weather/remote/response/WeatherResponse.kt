package com.example.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("weather") val weather: List<InternalWeatherResponse>,
    @SerializedName("main") val main: MainResponse,
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("name") val cityName: String,
    @SerializedName("cod") val cod: Int
) {
    companion object {
        fun getTestObject(): WeatherResponse {
         return   WeatherResponse(
                List(10) { InternalWeatherResponse.getTestObject() },
                MainResponse.getTestObject(),
                48448181,
                "Cairo",
                1
            )
        }
    }
}