package com.example.weatherapp.di

import android.content.Context


import com.example.data.weather.local.SharedPreferencesHelper
import com.example.data.weather.remote.WeatherApi
import com.example.data.weather.remote.WeatherClient
import com.example.data.weather.repository.RepositoryImpl
import com.example.domain.repositories.Repository
import com.example.weatherapp.BuildConfig


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {

        return WeatherClient(BuildConfig.API_KEY).api
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        sharedPreferencesHelper: SharedPreferencesHelper
    ): Repository {
        return RepositoryImpl(weatherApi, sharedPreferencesHelper)
    }



}