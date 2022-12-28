package com.sammengistu.quic.ui.home.data.states

import com.sammengistu.quic.ui.home.data.uiitem.HomeFeedUiItem

sealed class HomeFeedUiState {
    data class Success(val homeFeedUiItem: HomeFeedUiItem): HomeFeedUiState()
    data class Error(val exception: Throwable): HomeFeedUiState()
}