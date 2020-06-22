package com.aabhishek.weatherforecast.framework.datasource.cache.abstraction

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo

interface WeatherDaoService {

    suspend fun insertUpdatedWeather(weatherInfo: WeatherInfo): Long

}