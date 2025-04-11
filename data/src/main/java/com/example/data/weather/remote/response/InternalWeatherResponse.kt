package com.example.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class InternalWeatherResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
){
    companion object{
        fun getTestObject():InternalWeatherResponse{
            return InternalWeatherResponse(
                0,"main","description","icon"
            )
        }
    }
}
