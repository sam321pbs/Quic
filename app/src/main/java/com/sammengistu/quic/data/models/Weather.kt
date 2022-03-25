package com.sammengistu.quic.data.models

import com.sammengistu.quic.ui.fragments.home.CardViewType
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem

data class Weather(
    val temp: String,
    override val cardViewType: CardViewType = CardViewType.WEATHER
): CardViewAdapterItem