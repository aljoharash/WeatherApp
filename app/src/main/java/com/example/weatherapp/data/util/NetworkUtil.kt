package com.example.weatherapp.data.util

import android.net.ConnectivityManager
import com.example.weatherapp.common.Resource
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
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "Network Error"
                is HttpException -> getErrorMessage(e.code())
                else -> "Unknown Error"
            }
            emit(Resource.Failure(Exception(errorMessage)))
        }

    }

    private fun getErrorMessage(httpErrorCode: Int): String {
        return when (httpErrorCode) {
            400 -> "Bad Request"
            401 -> "Unauthorized"
            404 -> "Not Found"
            500 -> "Internal Server Error"
            else -> "Unknown Error"
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    }


}