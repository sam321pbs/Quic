package com.sammengistu.quic.ui.home.data

import com.sammengistu.quic.data.models.Market
import com.sammengistu.quic.ui.home.CardViewType

data class MarketUIItem(
    val shortName: String,
    val exchange: String,
    val changeAmount: String,
    val changePercent: String,
    val previousClose: String,
    val currentPrice: String,
    override val cardViewType: CardViewType = CardViewType.MARKET
): CardViewAdapterItem {
    companion object {
        fun transform(market: Market) = MarketUIItem(
            market.shortName ?: "",
            market.exchange ?: "",
            market.regularMarketChange?.fmt ?: "",
            market.regularMarketChangePercent?.fmt ?: "",
            market.regularMarketPreviousClose?.fmt ?: "",
            market.regularMarketPrice?.fmt ?: "",
        )
    }
}