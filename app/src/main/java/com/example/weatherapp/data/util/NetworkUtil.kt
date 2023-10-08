package com.example.weatherapp.data.util

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkUtil @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    fun <T : Any> safeApiCall(
        apiToBeCalled: suspend () -> T ,
    ): Flow<Resource<T>> = flow {

        val response = apiToBeCalled.invoke()

        try {
            emit(Resource.Loading)
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                emit(Resource.Success(response))
            }
        } catch (ex: Exception) {
            if(response.equals(3000)) {
                emit(
                    Resource.Failure(Exception("Unexpected Error"))
                )
            }
            else if(response.equals(1000)){
                emit(Resource.Failure(Exception("Network Error")))
            }
        }
    }

}