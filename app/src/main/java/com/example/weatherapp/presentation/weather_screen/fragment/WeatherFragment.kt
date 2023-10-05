package com.example.weatherapp.presentation.weather_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.weather_screen.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {

    private var binding: FragmentWeatherBinding?= null
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            weatherFragment = this@WeatherFragment
        }

        viewLifecycleOwner.lifecycleScope.launch {
            weatherViewModel.state.collect { state ->
                state.let {
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}