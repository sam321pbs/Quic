package com.sammengistu.quic.ui.home.viewholders

import com.sammengistu.quic.databinding.ItemMarketBinding
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.data.MarketUIItem

class MarketViewHolder(private val binder: ItemMarketBinding): BaseCardViewHolder(binder.root) {

    override fun bindView(item: CardViewAdapterItem) {
        binder.item = item as MarketUIItem
        binder.executePendingBindings()
    }
}