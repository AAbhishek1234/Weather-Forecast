package com.aabhishek.weatherforecast.business.data.cache.implementation

import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.cache.abstraction.WeatherDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherCacheDataSourceImpl
@Inject
constructor(
    private val weatherDaoService : WeatherDaoService
): WeatherCacheDataSource {

    override suspend fun insertUpdatedWeather(weatherInfo: WeatherInfo) = weatherDaoService.insertUpdatedWeather(weatherInfo)

    //Just for testing not used here
    override suspend fun getUpdatedWeather(): WeatherInfo? {
        return null
    }
}