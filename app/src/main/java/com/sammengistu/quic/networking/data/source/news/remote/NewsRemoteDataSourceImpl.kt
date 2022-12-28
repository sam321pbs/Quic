package com.sammengistu.quic.networking.data.source.news.remote

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.news.remote.retrofit.NewsApiService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {

    override suspend fun getTopNews(country: String, pageSize: String): Result<News?> =
        try {
            val response = newsApiService.getTopNews(country, pageSize)
            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(Exception(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
}