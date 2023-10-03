package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.FeelsLikeModel

data class FeelsLike(
    val day: Double ,
    val eve: Double ,
    val morn: Double ,
    val night: Double
)

fun FeelsLike.toFeelsLikeModel(): FeelsLikeModel {
    return FeelsLikeModel(day , eve , morn , night)
}