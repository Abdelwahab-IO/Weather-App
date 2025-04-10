package com.example.domain.useCase

import com.example.domain.entities.Weather
import com.example.domain.repositories.Repository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(repository: Repository) :
    BaseUseCase<Weather>(repository) {
    suspend operator fun invoke(city: String): Weather = unFoldRepoCall {
        repository.getWeatherByCity(city)
    }
}