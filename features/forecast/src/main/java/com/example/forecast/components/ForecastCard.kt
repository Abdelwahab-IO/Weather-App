package com.example.forecast.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.sharedUi.AppNetworkWeatherIcon
import com.example.domain.entities.Weather

@Composable
fun ForecastCard(forecast: Weather) {
    Card(
        modifier = Modifier.padding(8.dp)
            .size(150.dp,100.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = forecast.temperature.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            AppNetworkWeatherIcon(forecast.icon,forecast.condition)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "${forecast.minTemperature}° / ${forecast.maxTemperature}°",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
