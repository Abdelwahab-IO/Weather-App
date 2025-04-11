package com.example.data.weather.remote.response

data class ForecastWeatherItem(
    val dt: Long,
    val main: MainResponse,
    val weather: List<InternalWeatherResponse>
) {
    companion object {
        fun getTestObject(): ForecastWeatherItem {
            return ForecastWeatherItem(
                12345615,
                MainResponse.getTestObject(),
                List(10) { InternalWeatherResponse.getTestObject() }
            )
        }
    }
}

