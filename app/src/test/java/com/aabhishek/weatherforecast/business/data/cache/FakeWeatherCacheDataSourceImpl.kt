package com.aabhishek.weatherforecast.business.data.cache

import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo

class FakeWeatherCacheDataSourceImpl(
    private var weatherInfo: WeatherInfo? = null
) : WeatherCacheDataSource {

    override suspend fun insertUpdatedWeather(weatherInfo: WeatherInfo): Long {
        this.weatherInfo = weatherInfo
        return 1
    }

    override suspend fun getUpdatedWeather(): WeatherInfo? {
        return weatherInfo
    }
}