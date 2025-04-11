package com.example.forecast.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.SharedKeys
import com.example.domain.entities.Weather
import com.example.domain.useCase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState>
        get() = _forecastState
    private val _eventsChannel = Channel<ForecastEvents>()
    val eventsChannel: ReceiveChannel<ForecastEvents>
        get() = _eventsChannel


    init {
        processActions(ForecastActions.LoadData)
    }

    fun processActions(actions: ForecastActions) {
        when (actions) {
            ForecastActions.LoadData -> getForecast()
            ForecastActions.NavigateBack -> emitNavigateBackEvent()
            ForecastActions.ReloadData -> getForecast()
        }
    }

    private fun emitNavigateBackEvent() {
        viewModelScope.launch {
            _eventsChannel.send(ForecastEvents.NavigateBack)
        }
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
        this._forecastState.value = state

    }

    private fun emitFailureState(e: Throwable) {
        _forecastState.value = ForecastState.Error(e.message.toString())
        viewModelScope.launch {
            _eventsChannel.send(ForecastEvents.ErrorEvent(e.message.toString()))
        }
    }

    private fun emitLoadingState() {
        _forecastState.value = ForecastState.Loading
    }

    private fun retrieveScarcity(): String {
        return savedStateHandle.get<String>(SharedKeys.CITY_KEY)!!
    }
}