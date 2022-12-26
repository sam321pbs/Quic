package com.sammengistu.quic.networking.data.source.news.repository

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result

interface NewsRepository {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}