package com.example.data.weather.repository

import android.util.Log
import com.example.data.weather.local.SharedPreferencesHelper
import com.example.domain.entities.Weather
import com.example.domain.repositories.Repository
import com.example.data.weather.remote.WeatherApi
import com.example.data.weather.remote.toDomain
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) :
    Repository {
    override suspend fun getLastSearchedCity(): String {
        return sharedPreferencesHelper.city
    }

    override suspend fun setLastSearchedCity(city: String) {
        sharedPreferencesHelper.city = city
    }

    override suspend fun getWeatherByCity(city: String): Weather {
        return api.getWeather(city).toDomain()
    }


    override suspend fun getForecastByCity(city: String): List<Weather> {
        val res = api.getForecast(city)
        Log.d("vvvvvvvvvvvv",res.toString())
       return res .toDomain().reversed()

    }
}