package com.aabhishek.weatherforecast.framework.datasource.cache.mappers

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.cache.entity.WeatherCacheEntity
import com.aabhishek.weatherforecast.util.EntityMapper
import java.lang.Exception
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<WeatherCacheEntity, WeatherInfo>
{

    override fun mapFromEntity(entity: WeatherCacheEntity): WeatherInfo {
        return WeatherInfo(
            name = entity.name,
            windSpeed = entity.windSpeed,
            visibility = entity.visibility,
            temp = entity.temp,
            tempFeelsLike = entity.feelsLike,
            tempMin = entity.tempMin,
            tempMax = entity.tempMax,
            humidity = entity.humidity,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: WeatherInfo): WeatherCacheEntity {
        return WeatherCacheEntity(
            name = domainModel.name,
            windSpeed = domainModel.windSpeed,
            visibility = domainModel.visibility,
            temp = domainModel.temp,
            feelsLike = domainModel.tempFeelsLike,
            tempMin = domainModel.tempMin,
            tempMax = domainModel.tempMax,
            humidity = domainModel.humidity,
            description = domainModel.description
        )
    }
}