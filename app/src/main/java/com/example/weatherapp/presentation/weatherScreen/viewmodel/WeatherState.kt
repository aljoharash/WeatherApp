package com.example.weatherapp.presentation.weatherScreen.viewmodel

import com.example.weatherapp.data.remote.source.dto.WeatherDto

data class WeatherState(
    val weatherInfo: WeatherDto? = null ,
    val city: String? = "" ,
    val units: String = "metric" ,
    val error: String? = null,
    val isLoading: Boolean = false
    )

