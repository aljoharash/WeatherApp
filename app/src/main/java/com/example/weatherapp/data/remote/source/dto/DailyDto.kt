package com.example.weatherapp.data.remote.source.dto

data class DailyDto(
    val clouds: Int ,
    val dew_point: Double ,
    val dt: Int ,
    val feels_like: FeelsLikeDto ,
    val humidity: Int ,
    val moon_phase: Double ,
    val moonrise: Int ,
    val moonset: Int ,
    val pop: Double ,
    val pressure: Int ,
    val rain: Double ,
    val summary: String ,
    val sunrise: Int ,
    val sunset: Int ,
    val temp: TempDto ,
    val uvi: Double ,
    val weather: List<WeatherDataDto> ,
    val wind_deg: Int ,
    val wind_gust: Double ,
    val wind_speed: Double
)