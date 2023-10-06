package com.example.weatherapp.common

import android.content.Context
import android.location.Geocoder
import java.util.Locale

class LocationUtils(private val context: Context) {

    fun getCityName(lat: Double, long: Double): String? {
        var cityName: String?
        val geoCoder = Geocoder(context, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long, 1)
        cityName = address?.get(0)?.adminArea
        if (cityName == null) {
            cityName = address?.get(0)?.locality
            if (cityName == null) {
                cityName = address?.get(0)?.subAdminArea
            }
        }
        return cityName
    }
}