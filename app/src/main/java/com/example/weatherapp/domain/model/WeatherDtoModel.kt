package com.example.weatherapp.domain.model

import com.example.weatherapp.data.remote.source.dto.Current
import com.example.weatherapp.data.remote.source.dto.Daily
import com.example.weatherapp.data.remote.source.dto.Hourly
import com.example.weatherapp.data.remote.source.dto.Minutely

data class WeatherDtoModel(
    val current: Current ,
    val daily: List<Daily> ,
    val hourly: List<Hourly> ,
    val lat: Double ,
    val lon: Double ,
    val minutely: List<Minutely> ,
    val timezone: String ,
    val timezone_offset: Int
)