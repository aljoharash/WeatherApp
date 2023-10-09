package com.example.weatherapp.presentation.weatherScreen.view

import WeatherAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.weatherScreen.util.WeatherUtil.convertTimestampToDayOfWeek
import com.example.weatherapp.presentation.weatherScreen.util.WeatherUtil.getWeatherIcon
import com.example.weatherapp.presentation.weatherScreen.util.WeatherUtil.unitConversion
import com.example.weatherapp.presentation.weatherScreen.viewmodel.WeatherEvent
import com.example.weatherapp.presentation.weatherScreen.viewmodel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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
                    unitConversion(binding, weatherViewModel)
                    setWeatherInfo()
                    setRecyclerView()
                    displayErrorMsg()
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
            cityTextView.text = weatherState.city
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



    private fun displayErrorMsg(){
        val errorMsg = weatherViewModel.state.value.error
        if (!errorMsg.isNullOrBlank()) {
            Snackbar.make(requireView(), errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

}