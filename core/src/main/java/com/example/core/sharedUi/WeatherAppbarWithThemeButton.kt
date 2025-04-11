package com.example.core.sharedUi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.core.R
import com.example.core.compositonProviders.ThemeModeCompositionProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppbarWithThemeButton(title: String, onBackPressed: (() -> Unit)? = null) {
    TopAppBar(
        title = {
            Text(text = title, color = MaterialTheme.colorScheme.onPrimary)
        },
        navigationIcon = {
            if (onBackPressed != null)
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.menu)
                    )
                }
        },

        actions = {

            val currentThemeMode = ThemeModeCompositionProvider.LocalThemeMode.current
            Switch(
                checked = currentThemeMode.isDarkThemeState.value,
                onCheckedChange = { currentThemeMode.switchTheme() })


        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )

    )
}