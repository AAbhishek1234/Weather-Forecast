package com.aabhishek.weatherforecast.framework.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.aabhishek.weatherforecast.business.domain.usecases.UpdateWeatherForecast
import javax.inject.Inject
import javax.inject.Singleton

class WeatherWorkerFactory
    constructor (private val updateWeatherForecast: UpdateWeatherForecast) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return UpdateWeatherWorker(appContext, workerParameters, updateWeatherForecast)
    }
}