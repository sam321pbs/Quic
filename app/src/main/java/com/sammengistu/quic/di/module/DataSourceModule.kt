package com.sammengistu.quic.di.module

import com.sammengistu.quic.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quic.data.source.news.remote.NewsRemoteDataSourceImpl
import com.sammengistu.quic.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quic.data.source.weather.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindNewsRemoteDataSource(remoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindWeatherRemoteDataSource(remoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}