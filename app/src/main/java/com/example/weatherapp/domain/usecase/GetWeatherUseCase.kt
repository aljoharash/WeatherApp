package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.model.WeatherDtoModel
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double , lon: Double, units: String): Resource<WeatherDtoModel> {
        return repository.getWeather(lat , lon, units)
    }
}