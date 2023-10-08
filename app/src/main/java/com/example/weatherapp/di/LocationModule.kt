package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.common.LocationUtil
import com.example.weatherapp.data.repository.LocationRepositoryImp
import com.example.weatherapp.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    ): LocationRepository = LocationRepositoryImp(
        fusedLocationProviderClient ,
        application
    )

    @Provides
    @Singleton
    fun provideLocationUtils(@ApplicationContext context: Context): LocationUtil {
        return LocationUtil(context)
    }


}


