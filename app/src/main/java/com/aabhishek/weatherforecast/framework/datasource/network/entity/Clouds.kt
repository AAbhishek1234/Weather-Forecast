package com.aabhishek.weatherforecast.framework.datasource.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds (

    @SerializedName("all")
    @Expose
    val all : Double
)