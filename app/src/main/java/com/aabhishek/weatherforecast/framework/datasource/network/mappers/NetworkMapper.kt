package com.aabhishek.weatherforecast.framework.datasource.network.mappers

import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.network.entity.WeatherNetworkEntity
import com.aabhishek.weatherforecast.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<WeatherNetworkEntity, WeatherInfo>{

    override fun mapFromEntity(entity: WeatherNetworkEntity): WeatherInfo {
        return try {
            WeatherInfo(
                name = entity.name,
                windSpeed = entity.wind.speed.toInt(),
                visibility = entity.visibility.toInt(),
                temp = entity.main.temp.toFloat(),
                tempFeelsLike = entity.main.feels_like.toFloat(),
                tempMin = entity.main.temp_min.toFloat(),
                tempMax = entity.main.temp_max.toFloat(),
                humidity = entity.main.humidity.toInt(),
                description = entity.weatherList.get(0).description)
        } catch (e: Exception) {
            WeatherInfo()
        }

    }

    override fun mapToEntity(domainModel: WeatherInfo): WeatherNetworkEntity {
        TODO("Not yet implemented")
    }
}