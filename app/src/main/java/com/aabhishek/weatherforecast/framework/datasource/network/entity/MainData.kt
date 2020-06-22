package com.aabhishek.weatherforecast.framework.datasource.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainData(

    @SerializedName("temp")
    @Expose
    val temp: Double,

    @SerializedName("feels_like")
    @Expose
    val feels_like: Double,

    @SerializedName("temp_min")
    @Expose
    val temp_min: Double,

    @SerializedName("temp_max")
    @Expose
    val temp_max: Double,

    @SerializedName("pressure")
    @Expose
    val pressure: Double,

    @SerializedName("humidity")
    @Expose
    val humidity: Double

)