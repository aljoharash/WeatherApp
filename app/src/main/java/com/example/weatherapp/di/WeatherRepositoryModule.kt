package com.example.weatherapp.di

import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.repository.WeatherRepositoryImp
import com.example.weatherapp.data.util.NetworkUtil
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherRepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI , networkUtil: NetworkUtil): WeatherRepository {
        return WeatherRepositoryImp(api, networkUtil)
    }

}