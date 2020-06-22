package com.aabhishek.weatherforecast.framework.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.framework.datasource.cache.database.WeatherDao
import com.aabhishek.weatherforecast.framework.datasource.cache.mappers.CacheMapper
import com.aabhishek.weatherforecast.framework.worker.UpdateWeatherWorker
import com.aabhishek.weatherforecast.util.Constants.Companion.TAG_OUTPUT_ONCE
import com.aabhishek.weatherforecast.util.Constants.Companion.TAG_OUTPUT_REPEAT
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel
    @Inject
    constructor(
        private val weatherDao: WeatherDao,
        private val cacheMapper: CacheMapper,
        private val workManager: WorkManager): ViewModel() {


    private val TAG = MainViewModel::class.java.simpleName

    private val _updatedWeather: MutableLiveData<WeatherInfo> = Transformations.map(weatherDao.getUpdatedWeather()) {
        it?.let {
            Log.i(TAG,"LIVE DATA FROM DB")
            cacheMapper.mapFromEntity(it)
        }
    } as MutableLiveData<WeatherInfo>

    val updatedWeather: LiveData<WeatherInfo>
    get() = _updatedWeather

    fun enqueueRefreshWeather() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val refreshWeatherWork = PeriodicWorkRequest.Builder(UpdateWeatherWorker::class.java,20, TimeUnit.MINUTES, 2, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT_REPEAT)
            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

        val oneTimeWeatherUpdate = OneTimeWorkRequestBuilder<UpdateWeatherWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT_ONCE)
            .build()
        workManager.enqueue(oneTimeWeatherUpdate)
        workManager.enqueueUniquePeriodicWork(
            "Weather Update",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshWeatherWork
        )
        Log.i(TAG,"periodic work enqueued")
    }
}