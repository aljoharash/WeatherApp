package com.example.weatherapp.data.util

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class NetworkUtil @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    fun <T : Any> safeApiCall(
        apiToBeCalled: suspend () -> T ,
    ): Flow<Resource<T>> = flow {

        val response = apiToBeCalled()

        try {
            emit(Resource.Loading)
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                emit(Resource.Success(response))
            } else {
                emit(
                    Resource.Failure(Exception("Please check you Wi-Fi"))
                )
            }
        } catch (ex: IOException) {
            emit(
                Resource.Failure(Exception("Unexpected Error"))
            )
        }
    }

}