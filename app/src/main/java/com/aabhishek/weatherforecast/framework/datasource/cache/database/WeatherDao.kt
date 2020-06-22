package com.aabhishek.weatherforecast.framework.datasource.cache.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aabhishek.weatherforecast.framework.datasource.cache.entity.WeatherCacheEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdatedWeather(weatherEntity: WeatherCacheEntity): Long

    @Query("SELECT * FROM weather LIMIT 1")
    fun getUpdatedWeather(): LiveData<WeatherCacheEntity>

}