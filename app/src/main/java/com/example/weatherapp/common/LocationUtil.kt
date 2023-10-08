package com.example.weatherapp.common

import android.content.Context
import android.location.Geocoder
import com.example.weatherapp.data.util.Resource
import java.util.Locale

class LocationUtil(private val context: Context) {

    /*
    This function is responsible for converting latitude and longitude coordinates
    to get the City Name
    */
    @Suppress("DEPRECATION")
    fun getCityName(lat: Double , long: Double): String? {
        var cityName: String? = ""
        val geoCoder = Geocoder(context , Locale.getDefault())
        try {
            val address = geoCoder.getFromLocation(lat , long , 1)
            cityName = address?.get(0)?.adminArea
            Resource.Success(cityName)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(Exception("Could not retrieve city name"))
        }
        return cityName
    }
}