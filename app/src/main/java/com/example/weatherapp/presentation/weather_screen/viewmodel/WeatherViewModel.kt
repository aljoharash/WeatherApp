package com.example.weatherapp.presentation.weather_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase
): ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()


    init {
        onEvent(WeatherEvent.GetWeather)
    }

    fun onEvent(event: WeatherEvent){
        when(event)
        {
            is WeatherEvent.GetWeather -> getWeather()
        }
    }

    private fun getWeather(){
        viewModelScope.launch {
            weatherUseCase.invoke(0.0,0.0).also { weather ->
                _state.value = _state.value.copy(
                    current = weather.current,
                    daily = weather.daily,
                    hourly = weather.hourly,
                    lat = weather.lat,
                    lon = weather.lon,
                    minutely = weather.minutely,
                    timezone = weather.timezone,
                    timezone_offset = weather.timezone_offset
                )
            }
        }
    }
}