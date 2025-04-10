package com.example.data.weather.remote

import ForecastResponse
import WeatherItem
import com.example.domain.entities.Weather
import com.example.data.weather.remote.response.WeatherResponse


fun WeatherResponse.toDomain():Weather {
    val weatherInfo = weather.firstOrNull()
    return com.example.domain.entities.Weather(
        city = cityName,
        temperature = main.temp,
        feelsLike = main.feelsLike,
        maxTemperature = main.tempMax,
        minTemperature = main.tempMin,
        humidity = main.humidity.toDouble(),
        timeStamp = dateTime,
        condition = weatherInfo?.main ?: "Unknown",
        description = weatherInfo?.description ?: "No description",
        icon = weatherInfo?.icon ?: ""
    )
}



fun ForecastResponse.toDomain(): List<Weather> {
    return list.map { weatherItem ->
        mapWeatherItemToDomain(weatherItem, this.city.name)
    }
}


private fun mapWeatherItemToDomain(weatherItem: WeatherItem, cityName: String): Weather {

    val weatherInfo = weatherItem.weather.first()

    return Weather(
        city = cityName,
        temperature = weatherItem.main.temp,
        feelsLike = weatherItem.main.feels_like,
        maxTemperature = weatherItem.main.temp_max,
        minTemperature = weatherItem.main.temp_min,
        humidity = weatherItem.main.humidity.toDouble(),
        timeStamp = weatherItem.dt,
        condition = weatherInfo.main,  // Main condition (e.g., "Clouds", "Rain")
        description = weatherInfo.description,  // Detailed description of the weather
        icon = weatherInfo.icon  // Icon representing the weather condition
    )
}