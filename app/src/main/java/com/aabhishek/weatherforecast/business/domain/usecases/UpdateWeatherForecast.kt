package com.aabhishek.weatherforecast.business.domain.usecases

import android.content.ContentValues.TAG
import android.util.Log
import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.data.network.ApiResult
import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.util.printLogD
import com.aabhishek.weatherforecast.util.safeApiCall
import com.aabhishek.weatherforecast.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This usecase will fetch the data from API and update Local DB cache.
 */

@Singleton
class UpdateWeatherForecast
    @Inject
    constructor    (
    private val weatherCacheDataSource: WeatherCacheDataSource,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) {

    suspend operator fun invoke(latitude: String, longitude: String) {

        var weatherInfoResult =  safeApiCall(IO) {
            weatherNetworkDataSource.getWeatherForecast(latitude, longitude)
        }
        printLogD(TAG,"UpdateWeatherForecast weatherInfo : $weatherInfoResult")
        when (weatherInfoResult) {
            is ApiResult.Success -> {
                var weatherInfo = weatherInfoResult.value
                safeCacheCall(IO) {
                    weatherInfo?.let {
                        weatherCacheDataSource.insertUpdatedWeather(weatherInfo)
                        printLogD(TAG,"UpdateWeatherForecast data updated in db")
                    }
                }
            }
        }

    }
}