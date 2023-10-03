package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherAPI
): WeatherRepository {

    override suspend fun getWeather(lat: Double , lon: Double): WeatherDto {
        return api.getWeather(lat, lon)
    }


}