package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.WeatherDtoModel

data class WeatherDto(
    val current: Current ,
    val daily: List<Daily> ,
    val hourly: List<Hourly> ,
    val lat: Double ,
    val lon: Double ,
    val minutely: List<Minutely> ,
    val timezone: String ,
    val timezone_offset: Int
)

fun WeatherDto.toWeatherDtoModel(): WeatherDtoModel {
    return WeatherDtoModel(
        current ,
        daily ,
        hourly ,
        lat ,
        lon ,
        minutely ,
        timezone ,
        timezone_offset
    )
}