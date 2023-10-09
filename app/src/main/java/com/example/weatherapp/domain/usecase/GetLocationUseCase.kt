package com.example.weatherapp.domain.usecase

import android.location.Location
import com.example.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(): Location? {
        return locationRepository.getCurrentLocation()
    }

}