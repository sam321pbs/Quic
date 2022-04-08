package com.sammengistu.quic.data.source.news.remote.retrofit

import com.sammengistu.quic.data.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/docs/endpoints/top-headlines
interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("pageSize") pageSize: String
    ): Response<News?>
}