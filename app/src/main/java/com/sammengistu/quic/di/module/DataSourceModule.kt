package com.sammengistu.quic.di.module

import com.sammengistu.quic.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quic.data.source.news.remote.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource
}