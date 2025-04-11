package com.example.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
) {
    companion object {
        fun getTestObject(): MainResponse {
            return MainResponse(0.0, 1.0, 2.0, 3.0, 4, 5)
        }
    }

}