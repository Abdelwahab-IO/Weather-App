package com.example.core.compositonProviders

import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

class ThemeModeCompositionProvider {
    private val _isDarkThemeState = mutableStateOf(lastSavedValue)
    val isDarkThemeState: State<Boolean>
        get() = _isDarkThemeState

    fun switchTheme() {
        _isDarkThemeState.value = !_isDarkThemeState.value
        lastSavedValue =_isDarkThemeState.value
    }
    companion object{
       private var lastSavedValue =false
        val LocalThemeMode = compositionLocalOf { ThemeModeCompositionProvider() }
    }
}