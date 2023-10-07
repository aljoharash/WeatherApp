package com.example.weatherapp.presentation.weather_screen.viewmodel

import com.example.weatherapp.domain.model.WeatherDtoModel

data class WeatherState(
    val weatherInfo: WeatherDtoModel? = null ,
    val city: String? = "" ,
    val units: String = "metric" ,
    val isLoading: Boolean = false ,
    val error: String? = null
)

