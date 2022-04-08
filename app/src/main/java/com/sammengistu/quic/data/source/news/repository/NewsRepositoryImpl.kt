package com.sammengistu.quic.data.source.news.repository

import com.sammengistu.quic.data.models.News
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): NewsRepository {

    override suspend fun getTopNews(country: String, pageSize: String): Result<News?> {
        return withContext(ioDispatcher) {
            when (val response = remoteDataSource.getTopNews(country, pageSize)) {
                is Result.Success -> Result.Success(response.data)
                else -> Result.Error(Exception("Got a failed result"))
            }
        }
    }
}