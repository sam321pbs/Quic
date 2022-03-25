package com.sammengistu.quic.di.module

import com.sammengistu.quic.BuildConfig
import com.sammengistu.quic.data.source.news.remote.retrofit.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        val builder = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder().addQueryParameter(
                "apiKey", BuildConfig.NEWS_API_KEY
            ).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }
}