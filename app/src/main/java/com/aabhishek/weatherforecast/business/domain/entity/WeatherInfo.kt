package com.aabhishek.weatherforecast.business.domain.entity

data class WeatherInfo (
    val name: String = "",
    val windSpeed: Int = 0,
    val visibility: Int = 0,
    val temp: Float = 0f,
    val tempFeelsLike: Float = 0f,
    val tempMin: Float = 0f,
    val tempMax: Float = 0f,
    val humidity: Int = 0,
    val description: String =""
) {
fun shit() {
}
}