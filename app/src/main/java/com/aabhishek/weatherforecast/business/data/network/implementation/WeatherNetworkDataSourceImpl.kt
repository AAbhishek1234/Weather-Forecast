package com.aabhishek.weatherforecast.business.data.network.implementation

import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.network.abstraction.WeatherApiService
import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherNetworkDataSourceImpl
@Inject
constructor(
    private val weatherApiService: WeatherApiService
): WeatherNetworkDataSource {

    override suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherInfo {
        return weatherApiService.getWeatherForecast(latitude, longitude)
    }
}