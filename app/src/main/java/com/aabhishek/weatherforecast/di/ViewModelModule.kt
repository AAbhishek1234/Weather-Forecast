package com.aabhishek.weatherforecast.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aabhishek.weatherforecast.di.keys.MainViewModelKey
import com.aabhishek.weatherforecast.framework.presentation.viewmodel.MainViewModel
import com.aabhishek.weatherforecast.framework.presentation.viewmodel.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(MainViewModel::class)
    abstract fun bindAccountViewModel(mainViewModel: MainViewModel): ViewModel
}