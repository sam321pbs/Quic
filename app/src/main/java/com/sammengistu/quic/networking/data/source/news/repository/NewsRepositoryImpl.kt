package com.sammengistu.quic.networking.data.source.news.repository

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): NewsRepository {
    override suspend fun getTopNews(country: String, pageSize: String): Result<News?> {
        return withContext(ioDispatcher) {
            when (val result = remoteDataSource.getTopNews(country, pageSize)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> Result.Error(result.exception)
            }
        }
    }
}