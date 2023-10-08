package com.example.weatherapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.data.util.NetworkConstant
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl(NetworkConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkUtility(
        connectivityManager: ConnectivityManager ,
    ) = NetworkUtil(connectivityManager)
}

