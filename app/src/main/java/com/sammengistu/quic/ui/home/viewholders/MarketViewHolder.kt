package com.sammengistu.quic.ui.home.viewholders

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sammengistu.quic.databinding.ItemMarketBinding
import com.sammengistu.quic.databinding.ItemWeatherBinding
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.data.MarketUIItem
import com.sammengistu.quic.ui.home.data.WeatherUIItem

class MarketViewHolder(private val binder: ItemMarketBinding): BaseCardViewHolder(binder.root) {

    override fun bindView(item: CardViewAdapterItem) {
        binder.item = item as MarketUIItem
        binder.executePendingBindings()
    }
}