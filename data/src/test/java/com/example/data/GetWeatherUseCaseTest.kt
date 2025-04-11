package com.example.data

import com.example.data.weather.remote.response.WeatherResponse
import com.example.data.weather.remote.toDomain
import com.example.domain.repositories.Repository
import com.example.domain.useCase.GetWeatherUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class GetWeatherUseCaseTest {


    private lateinit var repository: Repository

    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        getWeatherUseCase = GetWeatherUseCase(repository)
    }

    @Test
    fun `test get weather by city returns as same as repo`() = runBlocking {
        val mockCity = "Los Angeles"
        val mockWeather = WeatherResponse.getTestObject().copy(cityName = mockCity).toDomain()

        `when`(repository.getWeatherByCity(mockCity)).thenReturn(mockWeather)


        val result = getWeatherUseCase.invoke(mockCity)

        verify(repository).getWeatherByCity(mockCity)


        assertEquals(mockWeather, result)
    }
}