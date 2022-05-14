package com.sammengistu.quic.ui.home.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammengistu.quic.testing.IdlingResources
import com.sammengistu.quic.ui.home.data.ArticleUIItem
import com.sammengistu.quic.ui.home.data.MarketUIItem
import com.sammengistu.quic.ui.home.data.WeatherUIItem
import com.sammengistu.quic.ui.home.data.transformers.transformArticlesToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformMarketToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformWeatherToUiItem
import com.sammengistu.quic.utils.LocationUtils
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants
import com.sammengistu.quicnetworking.data.source.finance.repository.FinanceRepository
import com.sammengistu.quicnetworking.data.source.news.NewsConstants
import com.sammengistu.quicnetworking.data.source.news.repository.NewsRepository
import com.sammengistu.quicnetworking.data.source.weather.WeatherConstants
import com.sammengistu.quicnetworking.data.source.weather.repository.WeatherRepository
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
        IdlingResources.newsIdlingResource.increment()
        viewModelScope.launch {
            when (
                val result = newsRepository.getTopNews(NewsConstants.Country.US, NewsConstants.Size.PAGE_MAX)
            ) {
                is Result.Success -> {
                    result.data?.let {
                        _news.value = it.articles?.transformArticlesToUiItem()
                    }
                    IdlingResources.newsIdlingResource.decrement()
                }
                is Result.Error -> {
                    IdlingResources.newsIdlingResource.decrement()
                    Log.e(TAG, "News: ${result.exception}")
                    _news.value = null
                }
            }
        }
    }

    fun fetchCurrentWeather(activity: Activity?) {
        IdlingResources.weatherIdlingResource.increment()
        LocationUtils.getUserLocation(activity) { location ->
            if (location != null) {
                viewModelScope.launch {
                    val result = weatherRepository.getCurrentWeather(
                        location.latitude.toString(),
                        location.longitude.toString(),
                        WeatherConstants.UNIT.IMPERIAL
                    )
                    when (result) {
                        is Result.Success -> {
                            _weather.value = result.data?.transformWeatherToUiItem()
                            IdlingResources.weatherIdlingResource.decrement()
                        }
                        is Result.Error -> {
                            Log.e(TAG, "Weather: ${result.exception}")
                            _weather.value = null
                            IdlingResources.weatherIdlingResource.decrement()
                        }
                    }
                }
            } else {
                Log.e(TAG, "Location came back null")
                IdlingResources.weatherIdlingResource.decrement()
            }
        }
    }

    fun fetchMarketSummary() {
        IdlingResources.financeIdlingResource.increment()
        viewModelScope.launch {
            when (val result = financeRepository.getMarketSummary(FinanceConstants.Language.LANG_EN, FinanceConstants.Region.US)) {
                is Result.Success -> {
                    result.data?.let { response ->
                        val list = response.marketSummaryResponse?.result?.transformMarketToUiItem()
                        _finance.value =
                            list?.filter { it.exchange == "SNP" || it.exchange == "DJI" || it.exchange == "NIM"}
                        IdlingResources.financeIdlingResource.decrement()
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "Market: ${result.exception}")
                    _finance.value = null
                    IdlingResources.financeIdlingResource.decrement()
                }
            }
        }
    }
}