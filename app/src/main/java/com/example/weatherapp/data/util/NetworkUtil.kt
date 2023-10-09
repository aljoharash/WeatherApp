package com.example.weatherapp.data.util

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkUtil @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    fun <T : Any> safeApiCall(
        apiCall: suspend () -> T ,
    ): Flow<Resource<T>> = flow {
        if (!isNetworkAvailable()) {
            emit(Resource.Failure(Exception("No internet connection")))
            return@flow
        }

        try {
            emit(Resource.Loading)
            val response = apiCall.invoke()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Failure(Exception("Network Error")))
        } catch (e: HttpException) {
            emit(Resource.Failure(Exception("Unknown API Error")))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    }


}