package com.example.weatherapp.data.remote

import com.example.weatherapp.common.Constant.API_KEY
import com.example.weatherapp.domain.model.WeatherDtoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double ,
        @Query("lon") lon: Double ,
        @Query("units") units: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherDtoModel

}