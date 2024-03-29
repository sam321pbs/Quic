package com.sammengistu.quicnetworking.di.module

import com.sammengistu.quic.networking.data.source.finance.repository.FinanceRepository
import com.sammengistu.quic.networking.data.source.finance.repository.FinanceRepositoryImpl
import com.sammengistu.quic.networking.data.source.news.repository.NewsRepository
import com.sammengistu.quic.networking.data.source.news.repository.NewsRepositoryImpl
import com.sammengistu.quic.networking.data.source.weather.repository.WeatherRepository
import com.sammengistu.quic.networking.data.source.weather.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repo: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindWeatherRepository(repo: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindFinanceRepository(repo: FinanceRepositoryImpl): FinanceRepository
}