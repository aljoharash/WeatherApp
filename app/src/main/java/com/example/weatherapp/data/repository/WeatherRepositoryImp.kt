package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.data.mappers.WeatherMappers
import com.example.weatherapp.domain.model.WeatherDtoModel
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepository {

    override suspend fun getWeather(lat: Double , lon: Double): Resource<WeatherDtoModel> {
        return try {
            Resource.Success(api.getWeather(lat, lon))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }


}