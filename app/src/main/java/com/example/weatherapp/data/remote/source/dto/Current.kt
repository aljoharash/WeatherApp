package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.CurrentModel

data class Current(
    val clouds: Int ,
    val dew_point: Double ,
    val dt: Int ,
    val feels_like: Double ,
    val humidity: Int ,
    val pressure: Int ,
    val sunrise: Int ,
    val sunset: Int ,
    val temp: Double ,
    val uvi: Double ,
    val visibility: Int ,
    val weather: List<Weather> ,
    val wind_deg: Int ,
    val wind_speed: Double
)

fun Current.toCurrentModel(): CurrentModel {
    return CurrentModel(
        clouds ,
        dew_point ,
        dt ,
        feels_like ,
        humidity ,
        pressure ,
        sunrise ,
        sunset ,
        temp ,
        uvi ,
        visibility ,
        weather ,
        wind_deg ,
        wind_speed
    )
}