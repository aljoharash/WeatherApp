package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.common.LocationUtils
import com.example.weatherapp.data.location.DefaultLocationTracker
import com.example.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient ,
        application: Application
    ): LocationTracker = DefaultLocationTracker(
        fusedLocationProviderClient ,
        application
    )

    @Provides
    @Singleton
    fun provideApplicationContext(
        application: Application
    ): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideLocationUtils(context: Context): LocationUtils {
        return LocationUtils(context)
    }


}


