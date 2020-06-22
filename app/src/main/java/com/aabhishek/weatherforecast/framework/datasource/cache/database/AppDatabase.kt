package com.aabhishek.weatherforecast.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aabhishek.weatherforecast.framework.datasource.cache.entity.WeatherCacheEntity

@Database(entities = [WeatherCacheEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

    companion object {
        val DATABASE_NAME = "weather_db"
    }
}