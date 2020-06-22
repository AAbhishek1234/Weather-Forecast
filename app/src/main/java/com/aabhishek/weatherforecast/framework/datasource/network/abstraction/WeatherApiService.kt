package com.aabhishek.weatherforecast.framework.datasource.network.abstraction

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity

interface WeatherApiService {

    suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherInfo
}