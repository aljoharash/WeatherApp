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
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "Network Error"
                is HttpException -> getErrorMessage(e.code())
                else -> "Unknown Error: ${e.message}"
            }
            emit(Resource.Failure(Exception(errorMessage)))
        }

    }

    private fun getErrorMessage(httpErrorCode: Int): String {
        return when (httpErrorCode) {
            400 -> "Bad Request: The server could not understand the request."
            401 -> "Unauthorized: Authentication failed or user does not have permissions."
            404 -> "Not Found: The requested resource was not found on the server."
            500 -> "Internal Server Error: Something went wrong on the server."
            else -> "Unknown Error: An unexpected error occurred."
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    }


}