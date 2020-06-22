package com.aabhishek.weatherforecast.framework.datasource.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherCacheEntity(
    //same Id for all so only 1 row is maintained and always updated.
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = "1",

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "windSpeed")
    val windSpeed: Int,

    @ColumnInfo(name = "visibility")
    val visibility: Int,

    @ColumnInfo(name = "temp")
    val temp: Float,

    @ColumnInfo(name = "tempFeelsLike")
    val feelsLike: Float,

    @ColumnInfo(name = "tempMin")
    val tempMin: Float,

    @ColumnInfo(name = "tempMax")
    val tempMax: Float,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "description")
    val description: String
) {
}