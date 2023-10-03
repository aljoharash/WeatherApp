package com.example.weatherapp.data.util

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception?) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}