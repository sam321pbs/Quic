package com.sammengistu.quic.ui.home.data.uiitem

import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem

data class MarketUIItem(
    val shortName: String,
    val exchange: String,
    val changeAmount: String,
    val changePercent: String,
    val previousClose: String,
    val currentPrice: String,
    override val cardViewType: CardViewType = CardViewType.MARKET
): CardViewAdapterItem