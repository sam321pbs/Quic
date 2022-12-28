package com.sammengistu.quic.networking.data.source.weather.remote

import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.weather.remote.retrofit.WeatherApiService
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
) : WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(
        lat: String,
        long: String,
        unit: String
    ): Result<CurrentWeather?> =
        try {
            val response = weatherApiService.getCurrentWeather(lat, long, unit)
            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(Exception(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
}