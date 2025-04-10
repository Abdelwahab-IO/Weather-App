package com.example.domain.useCase

import com.example.domain.entities.Weather
import com.example.domain.repositories.Repository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(repository: Repository) :
    BaseUseCase<List<Weather>>(repository) {
    suspend operator fun invoke(city: String): List<Weather> {
        return unFoldRepoCall {
            repository.getForecastByCity(city)
        }


    }

}