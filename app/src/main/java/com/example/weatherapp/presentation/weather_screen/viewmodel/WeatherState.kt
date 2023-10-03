package com.example.weatherapp.presentation.weather_screen.viewmodel

import com.example.weatherapp.data.remote.source.dto.Current
import com.example.weatherapp.data.remote.source.dto.Daily
import com.example.weatherapp.data.remote.source.dto.Hourly
import com.example.weatherapp.data.remote.source.dto.Minutely

data class WeatherState(
    val current: Current? = null ,
    val daily: List<Daily>? = emptyList() ,
    val hourly: List<Hourly>? = emptyList() ,
    val lat: Double = 0.0 ,
    val lon: Double = 0.0 ,
    val minutely: List<Minutely>? = emptyList() ,
    val timezone: String = "" ,
    val timezone_offset: Int = 0
)
