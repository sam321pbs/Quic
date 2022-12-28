package com.sammengistu.quic.ui.home.data.states

import com.sammengistu.quic.ui.home.data.uiitem.MarketUIItem

sealed class MarketUiState {
    data class Success(val markets: List<MarketUIItem>): MarketUiState()
    data class Error(val exception: Throwable): MarketUiState()
}