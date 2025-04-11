package com.example.data

import com.example.domain.repositories.Repository
import com.example.domain.useCase.GetLastSavedCityUseCase
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
class GetCityNameUseCaseTest {
    private lateinit var repository: Repository

    private lateinit var getLastSavedCityUseCase: GetLastSavedCityUseCase

    @Before
    fun setUp() {
        repository = mock()
        getLastSavedCityUseCase = GetLastSavedCityUseCase(repository)
    }

    @Test
    fun `test get last saved city match returned repo city`() = runBlocking {
        val mockCity = "New York"
        `when`(repository.getLastSearchedCity()).thenReturn(mockCity)
        val result = getLastSavedCityUseCase.invoke()
        verify(repository).getLastSearchedCity()
        assertEquals(mockCity, result)
    }
}