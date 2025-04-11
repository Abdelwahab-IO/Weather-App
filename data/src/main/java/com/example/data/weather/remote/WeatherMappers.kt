package com.example.data.weather.remote

import com.example.data.weather.remote.response.ForecastResponse
import com.example.data.weather.remote.response.ForecastWeatherItem
import com.example.domain.entities.Weather
import com.example.data.weather.remote.response.WeatherResponse


fun WeatherResponse.toDomain(): Weather {
    val weatherInfo = weather.firstOrNull()
    return Weather(
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
        weatherItem.mapWeatherItemToDomain(this.city.name)
    }
}


private fun ForecastWeatherItem.mapWeatherItemToDomain(cityName: String): Weather {

    val weatherInfo = weather.first()

    return Weather(
        city = cityName,
        temperature = main.temp,
        feelsLike = main.feelsLike,
        maxTemperature = main.tempMax,
        minTemperature = main.tempMin,
        humidity = main.humidity.toDouble(),
        timeStamp = dt,
        condition = weatherInfo.main,
        description = weatherInfo.description,
        icon = weatherInfo.icon
    )
}