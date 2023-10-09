package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(lat: Double , lon: Double , units: String): Flow<Resource<Response<WeatherDto>>>
}