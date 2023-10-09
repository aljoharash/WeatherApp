package com.example.weatherapp.di

import android.net.ConnectivityManager
import com.example.weatherapp.data.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {

    @Singleton
    @Provides
    fun provideNetworkUtil(
        connectivityManager: ConnectivityManager
    ) = NetworkUtil(connectivityManager)

}