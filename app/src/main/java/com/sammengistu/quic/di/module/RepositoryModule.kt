package com.sammengistu.quic.di.module

import com.sammengistu.quic.data.source.news.repository.NewsRepository
import com.sammengistu.quic.data.source.news.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repo: NewsRepositoryImpl): NewsRepository
}