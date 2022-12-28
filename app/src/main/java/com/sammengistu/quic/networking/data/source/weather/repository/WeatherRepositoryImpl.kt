package com.sammengistu.quic.networking.data.source.weather.repository

import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.weather.remote.WeatherRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
): WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: String,
        long: String,
        unit: String
    ): Flow<Result<CurrentWeather?>> {
        return flow{ emit(remoteDataSource.getCurrentWeather(lat, long, unit)) }
    }
}