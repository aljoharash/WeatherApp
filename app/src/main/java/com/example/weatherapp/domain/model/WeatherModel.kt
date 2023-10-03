package com.example.weatherapp.domain.model

data class WeatherModel(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)