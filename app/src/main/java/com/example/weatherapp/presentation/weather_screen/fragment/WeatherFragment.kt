package com.example.weatherapp.presentation.weather_screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.weather_screen.viewmodel.WeatherState
import com.example.weatherapp.presentation.weather_screen.viewmodel.WeatherViewModel
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

        viewLifecycleOwner.lifecycleScope.launch {
            weatherViewModel.state.collect { state ->
                state.let {
                    setWeatherInfo()
                    refresh()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setWeatherInfo() {

        binding?.city?.text = weatherViewModel.state.value.city

        binding?.degreeText?.text = getString(
            R.string.temp_degree, weatherViewModel.state.value.weatherInfo?.current?.temp.toString()
        )

        binding?.weatherDescription?.text =
            weatherViewModel.state.value.weatherInfo?.current?.weather?.get(0)?.description

        binding?.humidityValue?.text =
            weatherViewModel.state.value.weatherInfo?.current?.humidity.toString()

        binding?.windValue?.text =
            weatherViewModel.state.value.weatherInfo?.current?.wind_speed.toString()

        binding?.feelsLikeValue?.text =
            weatherViewModel.state.value.weatherInfo?.current?.feels_like.toString()


    }

    private fun refresh(){
        binding?.refreshButton?.setOnClickListener {
            weatherViewModel.getWeather()
        }
    }


}