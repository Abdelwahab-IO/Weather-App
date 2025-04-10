package com.example.data.weather.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_FILE_NAME = "PREF_FILE_NAME"
        private const val CITY_KEY = "CITY_KEY"

    }

    var city: String
        get() = sharedPreferences.getString(CITY_KEY, "Cairo")!!
        set(value) = sharedPreferences.edit().putString(CITY_KEY, value).apply()



    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
