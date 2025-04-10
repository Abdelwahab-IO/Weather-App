package com.example.domain.useCase

import com.example.domain.repositories.Repository
import javax.inject.Inject

class SetLastSavedCityUseCase @Inject constructor(repository: Repository):BaseUseCase<Unit>(repository) {
    suspend operator fun  invoke(city:String){
        unFoldRepoCall {
        repository.setLastSearchedCity(city)
        }
    }
}