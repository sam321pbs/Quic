package com.sammengistu.quic.networking.data.source.news.remote

import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result

interface NewsRemoteDataSource {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}