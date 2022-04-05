package com.sammengistu.quic.data.source.weather.remote

import android.util.Log
import com.sammengistu.quic.data.models.CurrentWeather
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.weather.remote.retrofit.WeatherApiService
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(lat: String, long: String): Result<CurrentWeather?> {
       return withContext(ioDispatcher) {
            return@withContext try {
                val response = weatherApiService.getCurrentWeather(lat, long, "imperial")
                if (response.isSuccessful) {
                    Result.Success(response.body()).also {
                        Log.d("WeatherDataSourceImpl", response.body().toString())
                    }
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}