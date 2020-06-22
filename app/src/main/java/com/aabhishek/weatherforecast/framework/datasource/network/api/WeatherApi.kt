package com.aabhishek.weatherforecast.framework.datasource.network.api

import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appId: String = ""
    ) : WeatherNetworkEntity

}