package com.example.weatherapp.presentation.weatherScreen.viewmodel

sealed class WeatherEvent {
    data class UnitChanged(val unit: String) : WeatherEvent()
    object GetWeatherInfo: WeatherEvent()
}
