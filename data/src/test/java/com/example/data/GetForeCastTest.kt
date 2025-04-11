package com.example.data

import com.example.domain.entities.Weather
import com.example.domain.repositories.Repository
import com.example.domain.useCase.GetForecastUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@RunWith(JUnit4::class)
class GetForeCastTest {


    private lateinit var repository: Repository

    private lateinit var getForecastUseCase: GetForecastUseCase

    @Before
    fun setUp() {
        repository = mock()
        getForecastUseCase = GetForecastUseCase(repository)
    }

    @Test
    fun `test get forecast by city returns the same entity as repo`() = runBlocking {
        val cityName = "New York"

        val mockWeatherList = listOf(Weather.getDummy().copy(cityName))


        `when`(repository.getForecastByCity(cityName)).thenReturn(mockWeatherList)


        val result = getForecastUseCase.invoke(cityName)


        verify(repository).getForecastByCity(cityName)


        assertEquals(mockWeatherList, result)
    }
}