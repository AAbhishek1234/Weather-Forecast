package com.aabhishek.weatherforecast.framework.datasource.network.implementation

import android.util.Log
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.network.abstraction.WeatherApiService
import com.aabhishek.weatherforecast.framework.datasource.network.api.WeatherApi
import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity
import com.aabhishek.weatherforecast.framework.datasource.network.mappers.NetworkMapper
import com.aabhishek.weatherforecast.util.Constants
import com.aabhishek.weatherforecast.util.printLogD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiServiceImpl
@Inject
constructor(
    private val weatherApi: WeatherApi,
    private val networkMapper: NetworkMapper
): WeatherApiService {

    private val TAG = WeatherApiServiceImpl::class.java.simpleName
    override suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherInfo {
        val forecast = weatherApi.getWeatherForecast(latitude, longitude, Constants.API_KEY)
        printLogD(TAG,"weather info fetched : $forecast")
        return networkMapper.mapFromEntity(forecast)

    }
}