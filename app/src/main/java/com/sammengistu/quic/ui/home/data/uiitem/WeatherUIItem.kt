package com.sammengistu.quic.ui.home.data.uiitem

import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem

data class WeatherUIItem(
    val cityName: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val humidity: String,
    val weatherGroup: String,
    val weatherDescription: String,
    val icon: String,
    override val cardViewType: CardViewType = CardViewType.WEATHER
) : CardViewAdapterItem
