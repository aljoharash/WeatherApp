package com.example.weatherapp.presentation.weather_screen.fragment

import WeatherAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.weather_screen.viewmodel.WeatherEvent
import com.example.weatherapp.presentation.weather_screen.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherFragment : Fragment() {

    private var binding: FragmentWeatherBinding? = null
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentWeatherBinding.inflate(inflater , container , false)
        binding = fragmentBinding


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        refreshButton()

        viewLifecycleOwner.lifecycleScope.launch {
            weatherViewModel.state.collect { state ->
                state.let {
                    unitConversion()
                    setWeatherInfo()
                    setRecyclerView()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setWeatherInfo() {
        val weatherState = weatherViewModel.state.value
        binding?.apply {
            city.text = weatherState.city
            degreeTextView.text =
                getString(
                    R.string.temp_degree ,
                    " " ,
                    weatherState.weatherInfo?.current?.temp.toString()
                )
            weatherDescriptionTextView.text =
                weatherState.weatherInfo?.current?.weather?.get(0)?.description
            humidityValueTextView.text = weatherState.weatherInfo?.current?.humidity.toString()
            windValueTextView.text = weatherState.weatherInfo?.current?.wind_speed.toString()
            feelsLikeValueTextView.text = weatherState.weatherInfo?.current?.feels_like.toString()
            weatherIconImageView.setImageResource(
                getWeatherIcon(
                    weatherState.weatherInfo?.current?.weather?.get(
                        0
                    )?.icon
                )
            )
        }
    }

    /*
    This function triggers a request to refresh the weather data from the API.
    It's responsible for initiating the data retrieval process and updating the UI
    with the latest weather information.
    */
    private fun refreshButton() {
        binding?.refreshButton?.setOnClickListener {
            weatherViewModel.onEvent(WeatherEvent.GetWeatherInfo)
        }
    }

    /*
    This function is responsible for setting up the RecyclerView with a list
    of 7-day weather forecast.
    */
    private fun setRecyclerView() {
        val weatherDailyInfo = weatherViewModel.state.value.weatherInfo?.daily

        val weatherDailyList = weatherDailyInfo?.map { info ->

            val day = convertTimestampToDayOfWeek(info.dt)
            val weatherIcon = info.weather[0].icon
            val iconResource = getWeatherIcon(weatherIcon)
            val tempMax = getString(R.string.temp_degree , "H:" , info.temp.max.toString())
            val tempMin = getString(R.string.temp_degree , "L:" , info.temp.min.toString())
            val description = info.weather[0].description
            ForecastData(day , iconResource , tempMax , tempMin , description)

        } ?: emptyList()
        val adapter = WeatherAdapter(weatherDailyList)
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }


    /*
     This function is responsible for setting up weather icons for each day
     based on the data retrieved from the API response.
    */
    private fun getWeatherIcon(iconString: String?): Int {
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
    Converts a Unix timestamp to the corresponding day of the week.
   */
    private fun convertTimestampToDayOfWeek(timestamp: Int): String {
        val dateFormat = SimpleDateFormat("EEEE" , Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000) // Convert seconds to milliseconds
        return dateFormat.format(date)
    }

    /*
    Converts temperature unit
    Celsius to Fahrenheit
    then refreshes the weather data
   */
    private fun unitConversion() {
        binding?.temperatureToggleButton?.setOnCheckedChangeListener { _ , isChecked ->
            if (isChecked) {
                weatherViewModel.onEvent(WeatherEvent.UnitChanged("imperial"))
                weatherViewModel.onEvent(WeatherEvent.GetWeatherInfo)
            } else {
                weatherViewModel.onEvent(WeatherEvent.UnitChanged("metric"))
                weatherViewModel.onEvent(WeatherEvent.GetWeatherInfo)
            }
        }
    }


}