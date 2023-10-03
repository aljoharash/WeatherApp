package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double , lon: Double): WeatherDto {
        return repository.getWeather(lat , lon)
    }
}