package com.sammengistu.quic.data.source.news.remote

import com.sammengistu.quic.data.models.News
import com.sammengistu.quic.data.source.Result

interface NewsRemoteDataSource {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}