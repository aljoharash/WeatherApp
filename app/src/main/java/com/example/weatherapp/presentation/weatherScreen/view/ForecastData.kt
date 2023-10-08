package com.example.weatherapp.presentation.weatherScreen.view

data class ForecastData(
    val dayName: String ,
    val weatherIcon: Int ,
    val temp_max: String ,
    val temp_min: String ,
    val description: String? = ""
)

