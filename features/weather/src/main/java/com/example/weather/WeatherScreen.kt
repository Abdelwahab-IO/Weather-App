package com.example.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.SharedKeys
import com.example.core.navigation.navigateToForecast
import com.example.core.navigation.navigateToSearch
import com.example.core.sharedUi.AppButton
import com.example.core.sharedUi.AppNetworkWeatherIcon
import com.example.core.sharedUi.ErrorScreenBody
import com.example.core.sharedUi.LoadingScreenBody
import com.example.core.sharedUi.UiState
import com.example.core.sharedUi.WeatherAppbarWithThemeButton


@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(topBar = {
        WeatherAppbarWithThemeButton("Weather")
    })


    { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                UiState.Success -> WeatherSuccessContent(viewModel, navController)
                UiState.Loading -> LoadingScreenBody()
                UiState.Failure -> ErrorScreenBody(viewModel.errorMsg) { viewModel.getLastSearchedCityWeather() }
            }
        }
    }

}

@Composable
fun WeatherSuccessContent(viewModel: WeatherViewModel, navController: NavController) {
    val weather = viewModel.weatherState.collectAsState().value!!

    LaunchedEffect(true) {

        val savedState = navController.currentBackStackEntry
            ?.savedStateHandle
        val result = savedState
            ?.getStateFlow<String?>(SharedKeys.CITY_KEY, null)
        result?.collect {
            viewModel.fetchWeather(it)
            savedState.remove<String?>(SharedKeys.CITY_KEY)


        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = weather.city,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AppNetworkWeatherIcon(weather.icon, weather.condition, size = 100)
        TemperatureRow("Temp", temp = weather.temperature)
        Spacer(Modifier.height(8.dp))
        TemperatureRow("Feels like", temp = weather.feelsLike)
        Spacer(Modifier.height(8.dp))
        TemperatureRow("Min", temp = weather.minTemperature)
        Spacer(Modifier.height(8.dp))
        TemperatureRow("Max", temp = weather.maxTemperature)
        Spacer(Modifier.height(8.dp))
        Text(
            text = weather.condition,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = weather.description,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(16.dp))
        Row {
            AppButton(text = "Search") {
                navController.navigateToSearch()
            }
            Spacer(Modifier.width(8.dp))
            AppButton(text = "Forecast") {
                navController.navigateToForecast(viewModel.lastSavedCity)
            }
        }

    }
}

@Composable
fun TemperatureRow(
    text: String,
    temp: Double
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$text: ",
            style = MaterialTheme.typography.bodyLarge,

            )
        Text(
            text = temp.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}