package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.core.compositonProviders.ThemeModeCompositionProvider
import com.example.weatherapp.navigation.AppNavigation
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private  val darkThemeProvider =  ThemeModeCompositionProvider()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            CompositionLocalProvider(ThemeModeCompositionProvider.LocalThemeMode provides darkThemeProvider) {
                WeatherAppTheme(darkTheme = darkThemeProvider.isDarkThemeState.value) {
                    AppNavigation()
                }
            }
        }
    }
}

