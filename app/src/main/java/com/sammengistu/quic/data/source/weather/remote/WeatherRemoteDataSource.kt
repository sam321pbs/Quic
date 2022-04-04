package com.sammengistu.quic.data.source.weather.remote

import com.sammengistu.quic.data.models.CurrentWeather
import com.sammengistu.quic.data.source.Result

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(lat: String, long: String): Result<CurrentWeather?>
}