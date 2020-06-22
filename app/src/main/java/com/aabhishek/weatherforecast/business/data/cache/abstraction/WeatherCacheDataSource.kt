package com.aabhishek.weatherforecast.business.data.cache.abstraction

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo

interface WeatherCacheDataSource {
    suspend fun insertUpdatedWeather(weatherInfo: WeatherInfo): Long

    //For testing
    suspend fun getUpdatedWeather(): WeatherInfo?
}