package com.example.domain.repositories

import com.example.domain.entities.Location

interface LocationRepository {
    suspend fun searchLocationByName(name: String): Location
    suspend fun saveLocationLocally(location: Location)
    suspend fun getLastSavedLocation(): Location
}