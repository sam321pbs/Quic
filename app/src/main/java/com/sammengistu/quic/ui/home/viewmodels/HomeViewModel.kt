package com.sammengistu.quic.ui.home.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammengistu.quic.data.models.FinanceResponse
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.finance.repository.FinanceRepository
import com.sammengistu.quic.data.source.news.NewsConstants
import com.sammengistu.quic.data.source.news.repository.NewsRepository
import com.sammengistu.quic.data.source.weather.repository.WeatherRepository
import com.sammengistu.quic.ui.home.data.ArticleUIItem
import com.sammengistu.quic.ui.home.data.WeatherUIItem
import com.sammengistu.quic.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val weatherRepository: WeatherRepository,
    private val financeRepository: FinanceRepository
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _news = MutableLiveData<List<ArticleUIItem>?>()
    val news = _news as LiveData<List<ArticleUIItem>?>

    private val _weather = MutableLiveData<WeatherUIItem?>()
    val weather = _weather as LiveData<WeatherUIItem?>

    private val _finance = MutableLiveData<FinanceResponse?>()
    val finance = _finance as LiveData<FinanceResponse?>

    fun fetchTopNews() {
        viewModelScope.launch {
            val result = newsRepository.getTopNews(NewsConstants.Country.US, NewsConstants.Size.MAX)
            when (result) {
                is Result.Success -> {
                    result.data?.let {
                        val articles = ArticleUIItem.transform(it)
                        _news.value = articles
                    }
                }
                is Result.Error -> {
                    Log.e("HomeViewModel", result.exception.toString())
                }
            }
        }
    }

    fun fetchCurrentWeather(activity: Activity?) {
        LocationUtils.getUserLocation(activity) { location ->
            if (location != null) {
                viewModelScope.launch {
                    val result = weatherRepository.getCurrentWeather(
                        location.latitude.toString(),
                        location.longitude.toString()
                    )
                    when (result) {
                        is Result.Success -> {
                            result.data?.let {
                                _weather.value = WeatherUIItem.transform(it)
                            }
                        }
                        is Result.Error -> {
                            Log.e(TAG, result.exception.toString())
                        }
                        else -> {
                            Log.e(TAG, "Error weather result")
                        }
                    }
                }
            } else {
                Log.e(TAG, "Location came back null")
            }
        }
    }

    fun fetchMarketSummary() {
        viewModelScope.launch {
            val result = financeRepository.getMarketSummary("en", "US")
            when (result) {
                is Result.Success -> {
                    result.data?.let {
                        _finance.value = it
                    }
                }
                is Result.Error -> {
                    Log.e("HomeViewModel", result.exception.toString())
                }
            }
        }
    }
}