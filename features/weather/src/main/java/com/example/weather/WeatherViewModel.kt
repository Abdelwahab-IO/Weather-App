package com.example.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.sharedUi.UiState
import com.example.domain.entities.Weather
import com.example.domain.useCase.GetLastSavedCityUseCase
import com.example.domain.useCase.GetWeatherUseCase
import com.example.domain.useCase.SetLastSavedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val setLastSavedCityUseCase: SetLastSavedCityUseCase,
    private val getLastSavedCityUseCase: GetLastSavedCityUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState
    var errorMsg: String = ""
        private set
    var lastSavedCity: String = ""
        private set
    private val _weatherState = MutableStateFlow<Weather?>(null)
    val weatherState: StateFlow<Weather?> = _weatherState

    init {
        getLastSearchedCityWeather()

    }

    fun getLastSearchedCityWeather() {
        viewModelScope.launch {
            if (lastSavedCity.isBlank())
                lastSavedCity = getLastSavedCityUseCase()

            fetchWeather(lastSavedCity)
        }

    }

    fun fetchWeather(city: String?) {
        if (city.isNullOrBlank())
            return
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {

                handleSuccessState(getWeatherUseCase(city))
                setLastSearchedCity(city = city)
            } catch (e: Throwable) {
                handleErrorState(e)
            }

        }
    }

    private fun handleSuccessState(weather: Weather) {

        _weatherState.value = weather
        _uiState.value = UiState.Success
        errorMsg = ""
    }

    private fun handleErrorState(e: Throwable) {
        errorMsg = e.message.toString()
        _uiState.value = UiState.Failure
    }

    private fun setLastSearchedCity(city: String) {
        if (lastSavedCity == city)
            return
        lastSavedCity = city
        viewModelScope.launch {
            setLastSavedCityUseCase(city)
        }
    }


}
