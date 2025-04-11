package com.example.data

import com.example.domain.repositories.Repository
import com.example.domain.useCase.SetLastSavedCityUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class SetLastSavedCityUseCaseTest {
    private lateinit var repository: Repository

    private lateinit var setLastSavedCityUseCase: SetLastSavedCityUseCase
    @Before
    fun setUp() {
        repository = mock()
        setLastSavedCityUseCase = SetLastSavedCityUseCase(repository)
    }

    @Test
    fun `test set last saved city calls repository setter`() = runBlocking {
        val mockCity = "Los Angeles"
        setLastSavedCityUseCase.invoke(mockCity)
        verify(repository).setLastSearchedCity(mockCity)
    }


}