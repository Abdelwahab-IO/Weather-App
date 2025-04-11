package com.example.forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.sharedUi.ErrorScreenBody
import com.example.core.sharedUi.LoadingScreenBody
import com.example.core.sharedUi.WeatherAppbarWithThemeButton
import com.example.forecast.components.ForecastCard
import com.example.forecast.components.TodayWeatherCard
import com.example.forecast.mvi.ForecastActions
import com.example.forecast.mvi.ForecastEvents
import com.example.forecast.mvi.ForecastState
import com.example.forecast.mvi.ForecastViewModel
import kotlinx.coroutines.flow.consumeAsFlow


@Composable
fun ForecastScreen(
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel()

) {
    Scaffold(topBar = {
        WeatherAppbarWithThemeButton(stringResource(R.string.days_forecast)) { navController.popBackStack() }
    }) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            when (val state = viewModel.forecastState.collectAsState().value) {
                is ForecastState.Error -> ErrorScreenBody(state.message) {
                    viewModel.processActions(ForecastActions.ReloadData)
                }

                ForecastState.Loading -> LoadingScreenBody()
                is ForecastState.Success -> ForecastSuccessBody(state)
            }

        }
    }
    LaunchedEffect(Unit) {
        viewModel.eventsChannel.consumeAsFlow().collect {
            if (it is ForecastEvents.NavigateBack)
                navController.popBackStack()
        }


    }

}

@Composable
fun ForecastSuccessBody(state: ForecastState.Success) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TodayWeatherCard(state.todayWeather)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.days_forecast),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(state.weekWeather) { forecast -> ForecastCard(forecast) }
        }

    }

}
