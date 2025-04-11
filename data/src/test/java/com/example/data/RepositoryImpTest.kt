package com.example.data

import com.example.data.weather.local.SharedPreferencesHelper
import com.example.data.weather.remote.WeatherApi
import com.example.data.weather.remote.response.City
import com.example.data.weather.remote.response.ForecastResponse
import com.example.data.weather.remote.response.WeatherResponse
import com.example.data.weather.remote.toDomain
import com.example.data.weather.repository.RepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever


@RunWith(JUnit4::class)
class RepositoryImplTest {


    private val api: WeatherApi = mock()
    private val sharedPreferencesHelper: SharedPreferencesHelper = mock()
    private val repository = RepositoryImpl(api, sharedPreferencesHelper)


    @Test
    fun `test get last searched city`() = runBlocking {
        val city = "New York"
        whenever(sharedPreferencesHelper.city).thenReturn(city)
        val result = repository.getLastSearchedCity()
        assertEquals(city, result)
    }

    @Test
    fun `test set last searched city`() = runBlocking {
        val city = "New York"
        repository.setLastSearchedCity(city)
        verify(sharedPreferencesHelper).city = city
    }

    @Test
    fun `test get weather by city returns valid entity from valid response`() = runBlocking {
        val city = "New York"
        val weatherResponse = WeatherResponse.getTestObject().copy(cityName = city)
        whenever(api.getWeather(city)).thenReturn(weatherResponse)
        val result = repository.getWeatherByCity(city)
        assertEquals(weatherResponse.toDomain(), result)
        assertEquals(city, result.city)
    }


    @Test
    fun `test get forecast by city returns valid entity from valid response`() = runBlocking {

        val city = "New York"
        val forecast = ForecastResponse.getTestObject().copy(city = City.getTestObject().copy(city))
        whenever(api.getForecast(city)).thenReturn(forecast)
        val result = repository.getForecastByCity(city)
        assertEquals(forecast.toDomain(), result)
        assertEquals(city, result[0].city)
    }
}
