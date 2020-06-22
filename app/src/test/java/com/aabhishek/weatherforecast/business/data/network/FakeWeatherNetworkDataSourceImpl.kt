package com.aabhishek.weatherforecast.business.data.network

import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo

class FakeWeatherNetworkDataSourceImpl(
    private var weatherInfo: WeatherInfo
) : WeatherNetworkDataSource {

    override suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherInfo {
        return weatherInfo
    }
}