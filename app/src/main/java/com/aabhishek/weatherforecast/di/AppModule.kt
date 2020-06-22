package com.aabhishek.weatherforecast.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.aabhishek.weatherforecast.business.data.cache.abstraction.WeatherCacheDataSource
import com.aabhishek.weatherforecast.business.data.cache.implementation.WeatherCacheDataSourceImpl
import com.aabhishek.weatherforecast.business.data.network.abstraction.WeatherNetworkDataSource
import com.aabhishek.weatherforecast.business.data.network.implementation.WeatherNetworkDataSourceImpl
import com.aabhishek.weatherforecast.business.domain.usecases.UpdateWeatherForecast
import com.aabhishek.weatherforecast.framework.BaseApplication
import com.aabhishek.weatherforecast.framework.datasource.cache.abstraction.WeatherDaoService
import com.aabhishek.weatherforecast.framework.datasource.cache.database.AppDatabase
import com.aabhishek.weatherforecast.framework.datasource.cache.database.AppDatabase.Companion.DATABASE_NAME
import com.aabhishek.weatherforecast.framework.datasource.cache.database.WeatherDao
import com.aabhishek.weatherforecast.framework.datasource.cache.implementation.WeatherDaoServiceImpl
import com.aabhishek.weatherforecast.framework.datasource.cache.mappers.CacheMapper
import com.aabhishek.weatherforecast.framework.datasource.network.abstraction.WeatherApiService
import com.aabhishek.weatherforecast.framework.datasource.network.api.WeatherApi
import com.aabhishek.weatherforecast.framework.datasource.network.implementation.WeatherApiServiceImpl
import com.aabhishek.weatherforecast.framework.datasource.network.mappers.NetworkMapper
import com.aabhishek.weatherforecast.framework.presentation.viewmodel.MainViewModelFactory
import com.aabhishek.weatherforecast.framework.worker.WeatherWorkerFactory
import com.aabhishek.weatherforecast.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder:  Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherApi(retrofitBuilder: Retrofit.Builder): WeatherApi {
        return retrofitBuilder
            .build()
            .create(WeatherApi::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherDao {
        return db.getWeatherDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherDaoService(weatherDao: WeatherDao,
                                 weatherMapper: CacheMapper): WeatherDaoService {
        return WeatherDaoServiceImpl(weatherDao, weatherMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherCacheDataSource(weatherDaoService : WeatherDaoService): WeatherCacheDataSource {
        return WeatherCacheDataSourceImpl(weatherDaoService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherNetworkDataSource(weatherApiService: WeatherApiService): WeatherNetworkDataSource {
        return WeatherNetworkDataSourceImpl(weatherApiService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherApiService(
        weatherApi: WeatherApi,
        networkMapper: NetworkMapper): WeatherApiService {
        return WeatherApiServiceImpl(weatherApi, networkMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWorkmanager(app: BaseApplication): WorkManager {
        return WorkManager.getInstance(app)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun workerFactory(updateWeatherForecast: UpdateWeatherForecast): WorkerFactory {
        return WeatherWorkerFactory(updateWeatherForecast)
    }


}