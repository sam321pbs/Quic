package com.sammengistu.quic.ui.home.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.models.FinanceResponse
import com.sammengistu.quic.networking.data.models.News
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.finance.FinanceConstants
import com.sammengistu.quic.networking.data.source.finance.repository.FinanceRepository
import com.sammengistu.quic.networking.data.source.news.NewsConstants
import com.sammengistu.quic.networking.data.source.news.repository.NewsRepository
import com.sammengistu.quic.networking.data.source.weather.WeatherConstants
import com.sammengistu.quic.networking.data.source.weather.repository.WeatherRepository
import com.sammengistu.quic.ui.home.data.states.HomeFeedUiState
import com.sammengistu.quic.ui.home.data.states.MarketUiState
import com.sammengistu.quic.ui.home.data.states.NewsUIState
import com.sammengistu.quic.ui.home.data.states.WeatherUiState
import com.sammengistu.quic.ui.home.data.transformers.transformArticlesToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformMarketToUiItem
import com.sammengistu.quic.ui.home.data.transformers.transformWeatherToUiItem
import com.sammengistu.quic.ui.home.data.uiitem.HomeFeedUiItem
import com.sammengistu.quic.ui.home.data.uiitem.WeatherUIItem
import com.sammengistu.quic.utils.UserLocation
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val weatherRepository: WeatherRepository,
    private val financeRepository: FinanceRepository,
) {

    private val _uiState = MutableLiveData<HomeFeedUiState>()
    val feedState = _uiState as LiveData<HomeFeedUiState>

    suspend fun loadFeed(location: UserLocation?) {
        val getWeatherFlow = if (location == null) {
            emptyFlow()
        } else {
            weatherRepository.getCurrentWeather(
                location.latitude.toString(),
                location.longitude.toString(),
                WeatherConstants.UNIT.IMPERIAL
            )
        }
        combine(
            newsRepository.getTopNews(NewsConstants.Country.US, NewsConstants.Size.PAGE_MAX),
            financeRepository.getMarketSummary(FinanceConstants.Language.LANG_EN, FinanceConstants.Region.US),
            getWeatherFlow
        ) { news, finance, weather ->
            val newsArticles = if (news is Result.Success<News>) {
                news.data?.articles?.transformArticlesToUiItem() ?: emptyList()
            } else {
                emptyList()
            }

            val markets = if (finance is Result.Success<FinanceResponse>) {
                finance.data?.marketSummaryResponse?.result?.transformMarketToUiItem() ?: emptyList()
            } else {
                emptyList()
            }

            val currentWeather: WeatherUIItem? = if (weather is Result.Success<CurrentWeather>) {
                weather.data?.transformWeatherToUiItem()
            } else {
                null
            }
            HomeFeedUiState.Success(
                HomeFeedUiItem(
                    MarketUiState.Success(markets.filter { it.exchange == "SNP" || it.exchange == "DJI" || it.exchange == "NIM"}),
                    NewsUIState.Success(newsArticles),
                    WeatherUiState.Success(currentWeather)
                )
            )
        }.collect {
            _uiState.value = it
        }
    }
}