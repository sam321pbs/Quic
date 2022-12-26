package com.sammengistu.quic.networking.data.source.weather.remote

import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.source.Result

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(lat: String, long: String, unit: String): Result<CurrentWeather?>
}