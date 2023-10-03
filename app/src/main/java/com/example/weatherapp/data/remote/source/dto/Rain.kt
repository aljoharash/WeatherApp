package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.RainModel

data class Rain(
    val `1h`: Double
)

fun Rain.toRainModel(): RainModel{
    return RainModel(`1h`)
}