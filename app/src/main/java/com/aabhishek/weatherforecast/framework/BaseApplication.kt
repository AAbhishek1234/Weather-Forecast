package com.aabhishek.weatherforecast.framework

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkerFactory
import com.aabhishek.weatherforecast.di.AppComponent
import com.aabhishek.weatherforecast.di.DaggerAppComponent
import com.aabhishek.weatherforecast.framework.worker.WeatherWorkerFactory
import javax.inject.Inject

open class BaseApplication : Application(), Configuration.Provider {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }
}