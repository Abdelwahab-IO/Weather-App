package com.example.forecast.mvi

sealed class ForecastActions {
    data object LoadData : ForecastActions()
    data object ReloadData : ForecastActions()
    data object NavigateBack : ForecastActions()
}