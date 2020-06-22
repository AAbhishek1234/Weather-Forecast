package com.aabhishek.weatherforecast.framework.datasource.cache.implementation

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.cache.abstraction.WeatherDaoService
import com.aabhishek.weatherforecast.framework.datasource.cache.database.WeatherDao
import com.aabhishek.weatherforecast.framework.datasource.cache.mappers.CacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDaoServiceImpl

@Inject
constructor(
    private val weatherDao: WeatherDao,
    private val weatherMapper: CacheMapper
) : WeatherDaoService {
    override suspend fun insertUpdatedWeather(weatherInfo: WeatherInfo) = weatherDao.insertUpdatedWeather(weatherMapper.mapToEntity(weatherInfo))
}