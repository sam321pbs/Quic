package com.sammengistu.quic.data.source.weather.repository

import com.sammengistu.quic.data.models.CurrentWeather
import com.sammengistu.quic.data.source.Result

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long: String): Result<CurrentWeather?>
}