package com.example.data

import com.example.data.weather.remote.response.InternalWeatherResponse
import com.example.data.weather.remote.response.MainResponse
import com.example.data.weather.remote.response.WeatherResponse
import com.example.data.weather.remote.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Before

@RunWith(JUnit4::class)
class WeatherResponseMapperTest {

    private lateinit var baseResponse: WeatherResponse

    @Before
    fun setup() {
        baseResponse = WeatherResponse(
            weather = listOf(
                InternalWeatherResponse(
                    id = 0,
                    main = "Clouds",
                    description = "broken clouds",
                    icon = "04d"
                )
            ),
            main = MainResponse(
                temp = 22.3,
                feelsLike = 21.7,
                tempMin = 20.0,
                tempMax = 24.0,
                humidity = 65,
                pressure = 1012,

            ),
            dateTime = 1681056000L,
            cityName = "Berlin",
            cod = 200,

        )
    }

    @Test
    fun `toDomain maps full WeatherResponse correctly`() {
        val domain = baseResponse.toDomain()

        assertEquals("Berlin", domain.city)
        assertEquals(22.3, domain.temperature, 0.0)
        assertEquals(21.7, domain.feelsLike, 0.0)
        assertEquals(24.0, domain.maxTemperature, 0.0)
        assertEquals(20.0, domain.minTemperature, 0.0)
        assertEquals(65.0, domain.humidity, 0.0)
        assertEquals(1681056000L, domain.timeStamp)
        assertEquals("Clouds", domain.condition)
        assertEquals("broken clouds", domain.description)
        assertEquals("04d", domain.icon)
    }

    @Test
    fun `toDomain handles empty weather list with default values`() {
        val response = baseResponse.copy(weather = emptyList())

        val domain = response.toDomain()

        assertEquals("Unknown", domain.condition)
        assertEquals("No description", domain.description)
        assertEquals("", domain.icon)
    }

    @Test
    fun `toDomain handles weather with blank strings`() {
        val response = baseResponse.copy(
            weather = listOf(
                InternalWeatherResponse(
                    id = 0,
                    main = "",
                    description = "",
                    icon = ""
                )
            )
        )

        val domain = response.toDomain()

        assertEquals("", domain.condition)
        assertEquals("", domain.description)
        assertEquals("", domain.icon)
    }

    @Test
    fun `toDomain handles humidity conversion correctly`() {
        val response = baseResponse.copy(main = baseResponse.main.copy(humidity = 80))

        val domain = response.toDomain()

        assertEquals(80.0, domain.humidity, 0.0)
    }
}
