package com.example.data.weather.remote.response

data class ForecastResponse(
    val list: List<ForecastWeatherItem>,
    val city: City
) {
    companion object {
        fun getTestObject(): ForecastResponse {
            return ForecastResponse(
                List(10) {
                    ForecastWeatherItem.getTestObject()
                }, City.getTestObject()
            )
        }
    }

}




