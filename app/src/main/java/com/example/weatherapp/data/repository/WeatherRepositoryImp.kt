package com.example.weatherapp.data.repository

import android.net.ConnectivityManager
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.data.util.NetworkUtil
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
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
    ): Flow<Resource<WeatherDto>> = flow {
        try {
            emit(Resource.Loading)
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                emit(Resource.Success(api.getWeather(lat , lon , units)))
            } else {
                emit(Resource.Failure(Exception("Wi-Fi Connection Error")))
            }
        } catch (e: IOException) {
            emit(Resource.Failure(Exception("Network Error")))
        } catch (e: Exception) {
            emit(Resource.Failure(Exception("Unexpected Error")))
        }
    }


}