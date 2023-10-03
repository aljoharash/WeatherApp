package com.example.weatherapp.data.remote.source.dto

import com.example.weatherapp.domain.model.TempModel

data class Temp(
    val day: Double ,
    val eve: Double ,
    val max: Double ,
    val min: Double ,
    val morn: Double ,
    val night: Double
)

fun Temp.toTempModel(): TempModel {
    return TempModel(day , eve , max , min , morn , night)
}