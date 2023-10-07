package com.example.weatherapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.common.Constant
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.repository.WeatherRepositoryImp
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Provides
    @Singleton
    fun provideApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI, connectivityManager: ConnectivityManager): WeatherRepository {
        return WeatherRepositoryImp(api, connectivityManager)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }


}

