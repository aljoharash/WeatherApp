package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.remote.source.dto.WeatherDto

interface WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double): WeatherDto
}