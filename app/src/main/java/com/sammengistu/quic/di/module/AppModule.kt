package com.sammengistu.quic.di.module

import android.content.Context
import com.sammengistu.quic.BuildConfig
import com.sammengistu.quic.data.models.news.News
import com.sammengistu.quic.data.source.news.remote.retrofit.NewsApiService
import com.sammengistu.quic.data.source.news.repository.NewsRepository
import com.sammengistu.quic.data.source.news.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ViewModelComponent::class)
@Module
abstract class AppModule {



}