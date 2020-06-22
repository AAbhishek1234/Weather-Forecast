package com.aabhishek.weatherforecast.framework.datasource.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoOrdinates(

    @SerializedName("lon")
    @Expose
    val lon: Double,

    @SerializedName("lat")
    @Expose
    val lat: Double
)