package com.example.weatherapp.presentation.weather_screen.viewmodel

sealed class WeatherEvent{
    object GetWeather: WeatherEvent()
}
