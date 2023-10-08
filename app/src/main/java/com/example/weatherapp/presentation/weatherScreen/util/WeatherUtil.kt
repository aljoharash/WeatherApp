package com.example.weatherapp.presentation.weatherScreen.util

import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.weatherScreen.viewmodel.WeatherEvent
import com.example.weatherapp.presentation.weatherScreen.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object WeatherUtil {

    /*
  This function is responsible for setting up weather icons for each day
  based on the data retrieved from the API response.
 */
    fun getWeatherIcon(iconString: String?): Int {
        val iconMappings = mapOf(
            "01d" to R.drawable.icon_01d ,
            "01n" to R.drawable.icon_01n ,
            "02d" to R.drawable.icon_02d ,
            "02n" to R.drawable.icon_02n ,
            "03n" to R.drawable.icon_cloudy_night ,
            "03d" to R.drawable.icon_cloudy_day ,
            "04n" to R.drawable.icon_cloudy_night ,
            "04d" to R.drawable.icon_cloudy_day ,
            "09d" to R.drawable.icon_09d ,
            "09n" to R.drawable.icon_09n ,
            "10d" to R.drawable.icon_10d ,
            "10n" to R.drawable.icon_10n ,
            "11d" to R.drawable.icon_11d ,
            "11n" to R.drawable.icon_11n ,
            "13d" to R.drawable.icon_13d ,
            "13n" to R.drawable.icon_13n ,
            "50d" to R.drawable.icon_50d ,
            "50n" to R.drawable.icon_50n ,
        )

        return iconMappings.getOrDefault(iconString , R.drawable.icon_01d)

    }

    /*
    Converts temperature unit
    Celsius to Fahrenheit
    then refreshes the weather data
    */
    fun unitConversion(binding: FragmentWeatherBinding? , weatherViewModel: WeatherViewModel) {
        binding?.temperatureToggleButton?.setOnCheckedChangeListener { _ , isChecked ->
            val unit = if (isChecked) "imperial" else "metric"
            weatherViewModel.onEvent(WeatherEvent.UnitChanged(unit))
            weatherViewModel.onEvent(WeatherEvent.GetWeatherInfo)
        }
    }

    /*
     Converts a Unix timestamp to the corresponding day of the week.
    */
    fun convertTimestampToDayOfWeek(timestamp: Int): String {
        val dateFormat = SimpleDateFormat("EEEE" , Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000) // Convert seconds to milliseconds
        return dateFormat.format(date)
    }


}