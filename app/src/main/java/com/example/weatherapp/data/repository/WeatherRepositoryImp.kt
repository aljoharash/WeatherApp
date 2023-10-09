package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.data.util.NetworkUtil
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherAPI ,
    private val networkUtility: NetworkUtil
) : WeatherRepository {

    override suspend fun getWeather(
        lat: Double ,
        lon: Double ,
        units: String
    ): Flow<Resource<WeatherDto>> = flow {
        networkUtility.safeApiCall {
            api.getWeather(lat , lon , units)
        }.collect { result -> emit(result) }
    }


}