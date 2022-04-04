package com.sammengistu.quic.data.source.weather.repository

import com.sammengistu.quic.data.models.CurrentWeather
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WeatherRepository {

    override suspend fun getCurrentWeather(lat: String, long: String): Result<CurrentWeather?> {
        return withContext(ioDispatcher) {
            when(val result = remoteDataSource.getCurrentWeather(lat, long)) {
                is Result.Success -> {
                    Result.Success(result.data)
                }
                else -> {
                    Result.Error(Exception("Error getting weather"))
                }
            }
        }
    }
}