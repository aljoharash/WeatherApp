package com.example.weatherapp.data.remote.dto

data class WeatherDto(
    val current: CurrentDto ,
    val daily: List<DailyDto> ,
    val hourly: List<HourlyDto> ,
    val lat: Double ,
    val lon: Double ,
    val minutely: List<MinutelyDto> ,
    val timezone: String ,
    val timezone_offset: Int
)
