package com.sammengistu.quic.data.source.news.repository

import com.sammengistu.quic.data.models.news.News
import com.sammengistu.quic.data.source.Result

interface NewsRepository {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}