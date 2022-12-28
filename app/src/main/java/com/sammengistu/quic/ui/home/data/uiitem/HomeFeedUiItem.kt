package com.sammengistu.quic.ui.home.data.uiitem

import com.sammengistu.quic.ui.home.data.states.MarketUiState
import com.sammengistu.quic.ui.home.data.states.NewsUIState
import com.sammengistu.quic.ui.home.data.states.WeatherUiState

data class HomeFeedUiItem(
    val marketUiState: MarketUiState,
    val newsUIState: NewsUIState,
    val weatherUiState: WeatherUiState,
)