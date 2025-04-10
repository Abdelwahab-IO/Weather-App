package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.SharedKeys
import com.example.core.navigation.FORECAST_ROUTE
import com.example.core.navigation.SEARCH_ROUTE
import com.example.core.navigation.WEATHER_ROUTE
import com.example.forecast.ForecastScreen
import com.example.search.SearchScreen
import com.example.weather.WeatherScreen


val argList = listOf(navArgument(SharedKeys.CITY_KEY) { type = NavType.StringType })

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = WEATHER_ROUTE) {
        composable(SEARCH_ROUTE) { SearchScreen(navController) }
        composable(WEATHER_ROUTE) { WeatherScreen(navController) }
        composable("$FORECAST_ROUTE/{${SharedKeys.CITY_KEY}}", arguments = argList) {
            ForecastScreen(navController)
        }
    }
}


