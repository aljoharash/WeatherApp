package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.WeatherModel

data class Weather(
    val description: String ,
    val icon: String ,
    val id: Int ,
    val main: String
)
