package com.sammengistu.quic.ui.home.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.finance.repository.FinanceRepository
import com.sammengistu.quic.data.source.news.NewsConstants
import com.sammengistu.quic.data.source.news.repository.NewsRepository
import com.sammengistu.quic.data.source.weather.repository.WeatherRepository
import com.sammengistu.quic.ui.home.data.ArticleUIItem
import com.sammengistu.quic.ui.home.data.MarketUIItem
import com.sammengistu.quic.ui.home.data.WeatherUIItem
import com.sammengistu.quic.ui.home.data.transformers.transformArticlesToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformMarketToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformWeatherToUiItem
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

    companion object {
        const val TAG = "HomeViewModel"
    }

    private val _news = MutableLiveData<List<ArticleUIItem>?>()
    val news = _news as LiveData<List<ArticleUIItem>?>

    private val _weather = MutableLiveData<WeatherUIItem?>()
    val weather = _weather as LiveData<WeatherUIItem?>

    private val _finance = MutableLiveData<List<MarketUIItem>?>()
    val finance = _finance as LiveData<List<MarketUIItem>?>

    fun fetchTopNews() {
        viewModelScope.launch {
            when (
                val result = newsRepository.getTopNews(NewsConstants.Country.US, NewsConstants.Size.MAX)
            ) {
                is Result.Success -> {
                    result.data?.let {
                        _news.value = it.articles?.transformArticlesToUiItem()
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "News: ${result.exception}")
                    _news.value = null
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
                            _weather.value = result.data?.transformWeatherToUiItem()
                        }
                        is Result.Error -> {
                            Log.e(TAG, "Weather: ${result.exception}")
                            _weather.value = null
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
            when (val result = financeRepository.getMarketSummary("en", "US")) {
                is Result.Success -> {
                    result.data?.let { response ->
                        val list = response.marketSummaryResponse?.result?.transformMarketToUiItem()
                        _finance.value =
                            list?.filter { it.exchange == "SNP" || it.exchange == "DJI" || it.exchange == "NIM"}
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "Market: ${result.exception}")
                    _finance.value = null
                }
            }
        }
    }
}