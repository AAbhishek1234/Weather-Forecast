package com.aabhishek.weatherforecast.framework.datasource.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys (

    @SerializedName("type")
    @Expose
    val type: Double,

    @SerializedName("id")
    @Expose
    val id: Double,

    @SerializedName("country")
    @Expose
    val country: String,

    @SerializedName("sunrise")
    @Expose
    val sunrise: Double,

    @SerializedName("sunset")
    @Expose
    val sumset: Double
)