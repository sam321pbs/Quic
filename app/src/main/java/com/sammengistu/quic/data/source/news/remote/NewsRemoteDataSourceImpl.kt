package com.sammengistu.quic.data.source.news.remote

import android.util.Log
import com.sammengistu.quic.data.models.News
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.news.remote.retrofit.NewsApiService
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): NewsRemoteDataSource {

    override suspend fun getTopNews(country: String, pageSize: String): Result<News> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = newsApiService.getTopNews(country, pageSize)
                if (response.isSuccessful) {
                    Result.Success(response.body()).also {
                        Log.d("NewsRemoteDataSourceImp", response.body().toString())
                    }
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

}