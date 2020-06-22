package com.aabhishek.weatherforecast.framework.datasource.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherNetworkEntity(
    @SerializedName("coord")
    @Expose
    val coord: CoOrdinates,

    @SerializedName("weather")
    @Expose
    val weatherList: List<WeatherDesc>,

    @SerializedName("base")
    @Expose
    val base: String,

    @SerializedName("main")
    @Expose
    val main: MainData,

    @SerializedName("visibility")
    @Expose
    val visibility: Double,

    @SerializedName("wind")
    @Expose
    val wind: Wind,

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds,

    @SerializedName("dt")
    @Expose
    val dt: Long,

    @SerializedName("sys")
    @Expose
    val sys: Sys,

    @SerializedName("timezone")
    @Expose
    val timeZone: Long,

    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("cod")
    @Expose
    val cod: Int
)