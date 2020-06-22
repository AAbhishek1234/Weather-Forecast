package com.aabhishek.weatherforecast.di

import android.app.Application
import com.aabhishek.weatherforecast.framework.BaseApplication
import com.aabhishek.weatherforecast.framework.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(application: BaseApplication)
}