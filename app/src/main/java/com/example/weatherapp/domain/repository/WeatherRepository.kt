package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.domain.model.WeatherDtoModel

interface WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double): WeatherDtoModel
}