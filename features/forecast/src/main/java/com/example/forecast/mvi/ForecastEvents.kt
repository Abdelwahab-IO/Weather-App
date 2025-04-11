package com.example.forecast.mvi

sealed class ForecastEvents {
    data class ErrorEvent(val errorMsg:String):ForecastEvents()
    data object NavigateBack:ForecastEvents()
}