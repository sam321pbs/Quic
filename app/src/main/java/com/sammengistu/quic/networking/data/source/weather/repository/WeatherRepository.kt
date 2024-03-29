package com.sammengistu.quic.networking.data.source.weather.repository

import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.source.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long: String, unit: String): Flow<Result<CurrentWeather?>>
}