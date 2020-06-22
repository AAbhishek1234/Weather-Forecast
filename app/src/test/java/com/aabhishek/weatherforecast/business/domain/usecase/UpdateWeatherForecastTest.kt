package com.aabhishek.weatherforecast.business.domain.usecase

import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.business.domain.usecases.UpdateWeatherForecast
import com.aabhishek.weatherforecast.di.DependencyContainer
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


/*
Test cases:

1. FetchDataFromNetworkUpdateCacheSuccess()
  a) a very simple test , just fetch the latest data from network and update cache, query cache to confirm success.

 */
class UpdateWeatherForecastTest {

    //system in test
    private val updateWeatherForecast: UpdateWeatherForecast

    //dependencies
    private val dependencyContainer: DependencyContainer
    private val weatherCacheDataSource: WeatherCacheDataSource
    private val weatherNetworkDataSource: WeatherNetworkDataSource

    init {
        dependencyContainer = DependencyContainer()
        dependencyContainer.build()
        weatherCacheDataSource = dependencyContainer.weatherCacheDataSource
        weatherNetworkDataSource = dependencyContainer.weatherNetworkDataSource

        updateWeatherForecast = UpdateWeatherForecast(weatherCacheDataSource, weatherNetworkDataSource)
    }

    @Test
    fun FetchDataFromNetworkUpdateCacheSuccess() = runBlocking {
        updateWeatherForecast("100", "100")
        val networkWeather = weatherNetworkDataSource.getWeatherForecast("100", "100")
        val cacheWeather = weatherCacheDataSource.getUpdatedWeather()
        assertTrue{ cacheWeather == networkWeather }
    }

}