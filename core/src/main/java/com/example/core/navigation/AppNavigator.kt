package com.example.core.navigation

import androidx.navigation.NavController


const val SEARCH_ROUTE = "search"
const val WEATHER_ROUTE = "weather"
const val FORECAST_ROUTE = "forecast"

fun NavController.navigateToWeather() {
    navigate(WEATHER_ROUTE){
        popUpTo(WEATHER_ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToSearch() {

    navigate(SEARCH_ROUTE){
        popUpTo(SEARCH_ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToForecast(city: String, ) {
    navigate("$FORECAST_ROUTE/$city"){
        popUpTo(FORECAST_ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}