package com.example.data

import com.example.data.weather.remote.response.City
import com.example.data.weather.remote.response.ForecastResponse
import com.example.data.weather.remote.response.ForecastWeatherItem
import com.example.data.weather.remote.response.InternalWeatherResponse
import com.example.data.weather.remote.response.MainResponse
import com.example.data.weather.remote.toDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ForecastResponseMapperTest {

    private lateinit var sampleWeatherItem: ForecastWeatherItem
    private lateinit var sampleForecastResponse: ForecastResponse
    private val sampleCityName = "Cairo"

    @Before
    fun setUp() {
        sampleWeatherItem = ForecastWeatherItem(
            dt = 1681056000L,
            main = MainResponse(
                temp = 30.0,
                feelsLike = 29.5,
                tempMin = 27.0,
                tempMax = 32.0,
                pressure = 1012,
                humidity = 40
            ),
            weather = listOf(
                InternalWeatherResponse(
                    id = 800,
                    main = "Clear",
                    description = "clear sky",
                    icon = "01d"
                )
            )
        )

        sampleForecastResponse = ForecastResponse(
            list = listOf(sampleWeatherItem),
            city = City(sampleCityName)
        )
    }

    @Test
    fun `toDomain maps forecast list with single item correctly`() {
        val result = sampleForecastResponse.toDomain()

        assertEquals(1, result.size)
        val weather = result.first()

        assertEquals(sampleCityName, weather.city)
        assertEquals(30.0, weather.temperature, 0.0)
        assertEquals(29.5, weather.feelsLike, 0.0)
        assertEquals(32.0, weather.maxTemperature, 0.0)
        assertEquals(27.0, weather.minTemperature, 0.0)
        assertEquals(40.0, weather.humidity, 0.0)
        assertEquals(1681056000L, weather.timeStamp)
        assertEquals("Clear", weather.condition)
        assertEquals("clear sky", weather.description)
        assertEquals("01d", weather.icon)
    }

    @Test
    fun `toDomain returns empty list when forecast list is empty`() {
        val emptyForecast = sampleForecastResponse.copy(list = emptyList())
        val result = emptyForecast.toDomain()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `toDomain handles multiple forecast items`() {
        val multiForecast = sampleForecastResponse.copy(
            list = listOf(sampleWeatherItem, sampleWeatherItem.copy(dt = 1681060000L))
        )

        val result = multiForecast.toDomain()

        assertEquals(2, result.size)
        assertEquals(1681056000L, result[0].timeStamp)
        assertEquals(1681060000L, result[1].timeStamp)
    }

    @Test
    fun `toDomain maps first weather only if multiple exist`() {
        val item = sampleWeatherItem.copy(
            weather = listOf(
                InternalWeatherResponse(0, "Rain", "light rain", "09d"),
                InternalWeatherResponse(1, "Clouds", "broken clouds", "03d")
            )
        )
        val forecast = sampleForecastResponse.copy(list = listOf(item))

        val result = forecast.toDomain()

        assertEquals("Rain", result.first().condition)
        assertEquals("light rain", result.first().description)
    }
}

