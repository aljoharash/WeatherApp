package com.example.weatherapp.presentation.weather_screen.viewmodel

import com.example.weatherapp.data.remote.source.dto.Current
import com.example.weatherapp.data.remote.source.dto.Daily
import com.example.weatherapp.data.remote.source.dto.Hourly
import com.example.weatherapp.data.remote.source.dto.Minutely
import com.example.weatherapp.domain.model.WeatherDtoModel

data class WeatherState(
    val weatherInfo: WeatherDtoModel? = null,
    val city: String? = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

