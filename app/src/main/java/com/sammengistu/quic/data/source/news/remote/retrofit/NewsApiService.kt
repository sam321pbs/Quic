package com.sammengistu.quic.data.source.news.remote.retrofit

import com.sammengistu.quic.data.models.news.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize: String
    ): Response<News>
}