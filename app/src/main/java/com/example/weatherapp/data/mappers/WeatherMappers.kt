package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.source.dto.Current
import com.example.weatherapp.data.remote.source.dto.Daily
import com.example.weatherapp.data.remote.source.dto.FeelsLike
import com.example.weatherapp.data.remote.source.dto.Hourly
import com.example.weatherapp.data.remote.source.dto.Minutely
import com.example.weatherapp.data.remote.source.dto.Rain
import com.example.weatherapp.data.remote.source.dto.Temp
import com.example.weatherapp.data.remote.source.dto.Weather
import com.example.weatherapp.data.remote.source.dto.WeatherDto
import com.example.weatherapp.domain.model.CurrentModel
import com.example.weatherapp.domain.model.DailyModel
import com.example.weatherapp.domain.model.FeelsLikeModel
import com.example.weatherapp.domain.model.HourlyModel
import com.example.weatherapp.domain.model.MinutelyModel
import com.example.weatherapp.domain.model.RainModel
import com.example.weatherapp.domain.model.TempModel
import com.example.weatherapp.domain.model.WeatherDtoModel
import com.example.weatherapp.domain.model.WeatherModel

class WeatherMappers {

    fun WeatherDto.toWeatherDtoModel(): WeatherDtoModel {
        return WeatherDtoModel(
            current ,
            daily ,
            hourly ,
            lat ,
            lon ,
            minutely ,
            timezone ,
            timezone_offset
        )
    }

    fun Weather.toWeatherModel(): WeatherModel {
        return WeatherModel(description , icon , id , main)
    }

    fun Temp.toTempModel(): TempModel {
        return TempModel(day , eve , max , min , morn , night)
    }

    fun Rain.toRainModel(): RainModel {
        return RainModel(`1h`)
    }

    fun Minutely.toMinutelyModel(): MinutelyModel {
        return MinutelyModel(dt, precipitation)
    }

    fun Hourly.toHourlyModel(): HourlyModel {
        return HourlyModel(
            clouds ,
            dew_point ,
            dt ,
            feels_like ,
            humidity ,
            pop ,
            pressure ,
            rain ,
            temp ,
            uvi ,
            visibility ,
            weather ,
            wind_deg ,
            wind_gust ,
            wind_speed
        )
    }

    fun FeelsLike.toFeelsLikeModel(): FeelsLikeModel {
        return FeelsLikeModel(day , eve , morn , night)
    }

    fun Daily.toDailyModel(): DailyModel {
        return DailyModel(
            clouds ,
            dew_point ,
            dt ,
            feels_like ,
            humidity ,
            moon_phase ,
            moonrise ,
            moonset ,
            pop ,
            pressure ,
            rain ,
            summary ,
            sunrise ,
            sunset ,
            temp ,
            uvi ,
            weather ,
            wind_deg ,
            wind_gust ,
            wind_speed
        )
    }

    fun Current.toCurrentModel(): CurrentModel {
        return CurrentModel(
            clouds ,
            dew_point ,
            dt ,
            feels_like ,
            humidity ,
            pressure ,
            sunrise ,
            sunset ,
            temp ,
            uvi ,
            visibility ,
            weather ,
            wind_deg ,
            wind_speed
        )
    }

}