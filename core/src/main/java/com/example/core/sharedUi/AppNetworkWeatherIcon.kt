package com.example.core.sharedUi

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.icon_formatter.IconFormatter

@Composable
fun AppNetworkWeatherIcon(iconId: String, condition: String, size: Int = 40) {
    AsyncImage(
        model = IconFormatter.formatIconLink(iconId),
        contentDescription = condition,
        modifier = Modifier.size(size.dp),
        error = painterResource(com.example.core.R.drawable.alert_error_svgrepo_com)
    )
}