package com.aabhishek.weatherforecast.util

import android.util.Log
import com.aabhishek.weatherforecast.BuildConfig.DEBUG

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d("$className:", "$message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}
