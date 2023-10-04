package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.MinutelyModel

data class Minutely(
    val dt: Int,
    val precipitation: Int
)

