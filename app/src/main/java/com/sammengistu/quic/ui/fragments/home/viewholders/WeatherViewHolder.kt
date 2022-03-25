package com.sammengistu.quic.ui.fragments.home.viewholders

import com.sammengistu.quic.data.models.Weather
import com.sammengistu.quic.databinding.ItemWeatherBinding
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem

class WeatherViewHolder(private val binder: ItemWeatherBinding): BaseCardViewHolder(binder.root) {

    override fun bindView(item: CardViewAdapterItem) {
        binder.item = item as Weather
        binder.executePendingBindings()
    }
}