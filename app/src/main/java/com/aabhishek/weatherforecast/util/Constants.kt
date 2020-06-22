package com.aabhishek.weatherforecast.util

class Constants {

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val TAG_OUTPUT_REPEAT = "OUTPUT_REPEAT"
        const val TAG_OUTPUT_ONCE = "OUTPUT_ONCE"
        const val API_KEY = "5ad7218f2e11df834b0eaf3a33a39d2a"

        //region Network Errors

        const val NETWORK_ERROR_UNKNOWN = "Unknown network error"
        const val NETWORK_ERROR = "Network error"
        const val NETWORK_ERROR_TIMEOUT = "Network timeout"

        //end region

        //region cache error
        const val CACHE_ERROR_UNKNOWN = "Unknown cache error"
        const val CACHE_ERROR = "Cache error"
        const val CACHE_ERROR_TIMEOUT = "Cache timeout"
        //end region
    }
}