package com.example.weatherapp.presentation.weather_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.LocationUtils
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase ,
    private val locationTracker: LocationTracker ,
    private val locationUtils: LocationUtils
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()


    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.UnitChanged -> {
                _state.value = _state.value.copy(units = event.unit)
            }
            is WeatherEvent.GetWeatherInfo -> {
                getWeatherInfo()
            }
        }
    }

    private fun getWeatherInfo() {
        viewModelScope.launch {
            Log.d(
                "USER_LOCATION" ,
                "${locationTracker.getCurrentLocation()?.latitude}, ${locationTracker.getCurrentLocation()?.longitude} "
            )

            locationTracker.getCurrentLocation()?.let { location ->
                when (val result = weatherUseCase.invoke(
                    location.latitude ,
                    location.longitude ,
                    _state.value.units
                )) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            weatherInfo = result.data ,
                            city = locationUtils.getCityName(
                                location.latitude ,
                                location.longitude
                            ) ,
                            error = null
                        )
                    }

                    is Resource.Failure -> {
                        _state.value = _state.value.copy(
                            weatherInfo = null ,
                            city = locationUtils.getCityName(
                                location.latitude ,
                                location.longitude
                            ) ,
                            error = result.exception.toString()
                        )

                    }
                }
            }

            Log.d("WEATHER_TEMP" , "${_state.value.weatherInfo?.current?.temp}")

        }
    }


}