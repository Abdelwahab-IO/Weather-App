package com.example.forecast.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.SharedKeys
import com.example.domain.entities.Weather
import com.example.domain.useCase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    val forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)


    init {

        getForecast()
    }

    private fun getForecast() {
        emitLoadingState()
        viewModelScope.launch {
            try {
                emitSuccessState(getForecastUseCase(retrieveScarcity()))
            } catch (e: Throwable) {
                emitFailureState(e)
            }

        }
    }

    private fun emitSuccessState(list: List<Weather>) {
        val state = ForecastState.Success(
            todayWeather = list[0],
            weekWeather = list.subList(1, 8)
        )
        this.forecastState.value = state

    }

    private fun emitFailureState(e: Throwable) {
        forecastState.value = ForecastState.Error(e.message.toString())
    }

    private fun emitLoadingState() {
        forecastState.value = ForecastState.Loading
    }

    private fun retrieveScarcity(): String {
       return savedStateHandle.get<String>(SharedKeys.CITY_KEY)!!
    }
}