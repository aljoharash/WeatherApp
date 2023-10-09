package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double ,
        lon: Double ,
        units: String
    ): Flow<Resource<WeatherDto>> {
        return repository.getWeather(lat , lon , units)
    }
}