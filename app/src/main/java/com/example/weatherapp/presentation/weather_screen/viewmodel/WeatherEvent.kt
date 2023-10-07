package com.example.weatherapp.presentation.weather_screen.viewmodel

sealed class WeatherEvent {
    data class UnitChanged(val unit: String) : WeatherEvent()
    object GetWeatherInfo: WeatherEvent()
}
