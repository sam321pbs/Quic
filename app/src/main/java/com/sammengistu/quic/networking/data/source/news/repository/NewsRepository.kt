package com.sammengistu.quic.networking.data.source.news.repository

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopNews(country: String, pageSize: String): Flow<Result<News?>>
}