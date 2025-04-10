package com.example.forecast.mvi

import com.example.core.sharedUi.UiState
import com.example.domain.entities.Weather
sealed class ForecastState {
   data object Loading : ForecastState()

    data class Success(
        val todayWeather: Weather,
        val weekWeather: List<Weather>
    ) : ForecastState()

    data class Error(val message: String) : ForecastState()
}