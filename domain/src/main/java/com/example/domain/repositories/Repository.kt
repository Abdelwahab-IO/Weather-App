package com.example.domain.repositories

import com.example.domain.entities.Weather

interface Repository {
 suspend fun getLastSearchedCity(): String
 suspend fun setLastSearchedCity(city: String)

 suspend   fun getWeatherByCity(city:String): Weather
 suspend   fun getForecastByCity(city: String):List<Weather>


}