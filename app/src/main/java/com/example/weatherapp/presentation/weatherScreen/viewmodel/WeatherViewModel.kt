package com.example.weatherapp.presentation.weatherScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.LocationUtil
import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.LocationRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase ,
    private val locationTracker: LocationRepository ,
    private val locationUtils: LocationUtil
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
                weatherUseCase(
                    location.latitude ,
                    location.longitude ,
                    _state.value.units
                ).collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                weatherInfo = result.data as WeatherDto? ,
                                city = locationUtils.getCityName(
                                    location.latitude ,
                                    location.longitude
                                ) ,
                                error = null ,
                                isLoading = false
                            )
                        }

                        is Resource.Failure -> {
                            _state.value = _state.value.copy(
                                weatherInfo = null ,
                                city = locationUtils.getCityName(
                                    location.latitude ,
                                    location.longitude
                                ) ,
                                error = result.exception.toString() ,
                                isLoading = false
                            )

                        }

                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )

                        }
                    }
                }
            }

            Log.d("WEATHER_TEMP" , "${_state.value.weatherInfo?.current?.temp}")

        }
    }


}