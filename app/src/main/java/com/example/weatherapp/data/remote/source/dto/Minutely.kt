package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.MinutelyModel

data class Minutely(
    val dt: Int,
    val precipitation: Int
)

fun Minutely.toMinutelyModel(): MinutelyModel {
    return MinutelyModel(dt, precipitation)
}

