package com.example.domain.entities

data class Weather(
    val city: String,
    val temperature: Double,
    val feelsLike: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val humidity: Double,
    val timeStamp: Long,
    val condition: String,
    val description: String,
    val icon: String,
) {
    companion object {
        fun getDummy(): Weather {
            return Weather(
                city = "Sample City",
                temperature = 25.0,
                feelsLike = 27.0,
                maxTemperature = 30.0,
                minTemperature = 20.0,
                humidity = 60.0,
                timeStamp = System.currentTimeMillis(),
                condition = "Clear",
                description = "Clear sky",
                icon = "clear_icon"
            )
        }
    }
}
