package com.example.forecast.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.entities.Weather


@Composable
fun TodayWeatherCard(today: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = today.city,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Load the SVG icon using Accompanist's rememberSvgPainter.

            AsyncImage(
                model = "http://openweathermap.org/img/wn/${today.icon}@4x.png",
                contentDescription = today.description,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds,
                error = painterResource(com.example.core.R.drawable.alert_error_svgrepo_com)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${today.temperature}°",
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = today.condition,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
