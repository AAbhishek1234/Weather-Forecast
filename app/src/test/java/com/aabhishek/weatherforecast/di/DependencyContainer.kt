package com.aabhishek.weatherforecast.di

import com.aabhishek.weatherforecast.business.data.cache.FakeWeatherCacheDataSourceImpl
import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.data.network.FakeWeatherNetworkDataSourceImpl
import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.util.isUnitTest

class DependencyContainer {

    lateinit var weatherCacheDataSource: WeatherCacheDataSource
    lateinit var weatherNetworkDataSource: WeatherNetworkDataSource


    init {
        isUnitTest = true
    }
    fun build() {
        weatherCacheDataSource = FakeWeatherCacheDataSourceImpl()

        weatherNetworkDataSource = FakeWeatherNetworkDataSourceImpl(
            WeatherInfo(
                name = "mars",
                windSpeed = 100,
                visibility = 1,
                temp = 300f,
                tempFeelsLike = 1000f,
                tempMax = 2000f,
                tempMin = 100f,
                humidity = 120,
                description = "Life Found"
            )
        )
    }
}