package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.HourlyModel

data class Hourly(
    val clouds: Int ,
    val dew_point: Double ,
    val dt: Int ,
    val feels_like: Double ,
    val humidity: Int ,
    val pop: Double ,
    val pressure: Int ,
    val rain: Rain ,
    val temp: Double ,
    val uvi: Double ,
    val visibility: Int ,
    val weather: List<Weather> ,
    val wind_deg: Int ,
    val wind_gust: Double ,
    val wind_speed: Double
)

fun Hourly.toHourlyModel(): HourlyModel {
    return HourlyModel(
        clouds ,
        dew_point ,
        dt ,
        feels_like ,
        humidity ,
        pop ,
        pressure ,
        rain ,
        temp ,
        uvi ,
        visibility ,
        weather ,
        wind_deg ,
        wind_gust ,
        wind_speed
    )
}