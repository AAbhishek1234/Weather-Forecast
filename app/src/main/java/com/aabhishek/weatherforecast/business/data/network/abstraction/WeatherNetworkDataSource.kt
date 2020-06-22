package com.aabhishek.weatherforecast.business.data.network.abstraction

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity

interface WeatherNetworkDataSource {

    suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherInfo
}