package com.example.weatherapp.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*
This class implementation was guided by ChatGPT,
due to unfamiliarity with utilizing location services in Kotlin.
*/
@ExperimentalCoroutinesApi
class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient ,
    private val context: Context
) : LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        return suspendCoroutine { continuation ->
            //Check if the user has accepted the location request
            if (ContextCompat.checkSelfPermission(
                    context ,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                //If True -> Resume coroutine with location result
                locationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    continuation.resume(location)
                }
            } else {
                //If False -> Resume coroutine with null location result
                continuation.resume(null)
            }
        }
    }
}