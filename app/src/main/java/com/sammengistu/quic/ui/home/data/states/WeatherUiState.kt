package com.sammengistu.quic.ui.home.data.states

import com.sammengistu.quic.ui.home.data.uiitem.WeatherUIItem

sealed class WeatherUiState {
    data class Success(val weather: WeatherUIItem?): WeatherUiState()
    data class Error(val exception: Throwable?): WeatherUiState()
}