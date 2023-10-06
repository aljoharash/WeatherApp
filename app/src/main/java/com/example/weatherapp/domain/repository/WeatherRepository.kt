package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.model.WeatherDtoModel

interface WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double, units: String): Resource<WeatherDtoModel>
}