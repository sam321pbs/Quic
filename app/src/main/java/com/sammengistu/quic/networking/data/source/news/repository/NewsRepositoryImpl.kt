package com.sammengistu.quic.networking.data.source.news.repository

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.news.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
): NewsRepository {
    override suspend fun getTopNews(country: String, pageSize: String): Flow<Result<News?>> = flow {
        emit(remoteDataSource.getTopNews(country, pageSize))
    }
}