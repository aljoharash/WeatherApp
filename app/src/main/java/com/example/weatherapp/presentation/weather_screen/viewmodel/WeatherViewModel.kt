package com.example.weatherapp.presentation.weather_screen.viewmodel

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase ,
    private val locationTracker: LocationTracker ,
    private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    init {
        getWeather()
    }

    fun getWeather() {
        viewModelScope.launch {
            Log.d(
                "USER_LOCATION" ,
                "${locationTracker.getCurrentLocation()?.latitude}, ${locationTracker.getCurrentLocation()?.longitude} "
            )

            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = weatherUseCase.invoke(location.latitude, location.longitude)){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            weatherInfo = result.data ,
                            city = getCityName(location.latitude, location.longitude),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Failure -> {
                        _state.value = _state.value.copy(
                            weatherInfo = null ,
                            city = getCityName(location.latitude, location.longitude),
                            isLoading = true,
                            error = result.exception.toString()
                        )

                    }
                    is Resource.Loading -> {}
                }
            }

            Log.d("WEATHER_TEMP" , "${_state.value.weatherInfo?.current?.temp}")

        }
    }


    private fun getCityName(lat: Double,long: Double): String? {
        var cityName: String?
        val geoCoder = Geocoder(context, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat,long,1)
        cityName = address?.get(0)?.adminArea
        if (cityName == null){
            cityName = address?.get(0)?.locality
            if (cityName == null){
                cityName = address?.get(0)?.subAdminArea
            }
        }
        return cityName
    }

}