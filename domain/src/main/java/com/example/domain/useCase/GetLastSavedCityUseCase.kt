package com.example.domain.useCase

import com.example.domain.repositories.Repository
import javax.inject.Inject

class GetLastSavedCityUseCase @Inject constructor(repository: Repository) :
    BaseUseCase<String>(repository) {
    suspend operator fun invoke(): String {
        return unFoldRepoCall {
            repository.getLastSearchedCity()
        }
    }
}