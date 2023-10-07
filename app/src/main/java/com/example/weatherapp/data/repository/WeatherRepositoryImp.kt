package com.example.weatherapp.data.repository

import android.net.ConnectivityManager
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.model.WeatherDtoModel
import com.example.weatherapp.domain.repository.WeatherRepository
import java.io.IOException
import javax.inject.Inject

@Suppress("DEPRECATION")
class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherAPI ,
    private val connectivityManager: ConnectivityManager
) : WeatherRepository {
    override suspend fun getWeather(
        lat: Double ,
        lon: Double ,
        units: String
    ): Resource<WeatherDtoModel> {
        return try {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                Resource.Success(api.getWeather(lat , lon , units))
            } else {
                Resource.Failure(Exception("Wi-Fi Connection Error"))
            }
        } catch (e: IOException) {
            Resource.Failure(Exception("Network Error"))
        } catch (e: Exception) {
            Resource.Failure(Exception("Unexpected Error"))
        }
    }

}