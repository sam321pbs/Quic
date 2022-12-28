package com.sammengistu.quic.ui.home.viewholders

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sammengistu.quic.databinding.ItemWeather2Binding
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.data.uiitem.WeatherUIItem

class WeatherViewHolder(private val binder: ItemWeather2Binding): BaseCardViewHolder(binder.root) {

    override fun bindView(item: CardViewAdapterItem) {
        binder.item = item as WeatherUIItem
        binder.executePendingBindings()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("weatherIcon")
        fun loadImage(view: ImageView, weatherIcon: String?) {
            Glide.with(view.context)
                .load(weatherIcon)
                .override(150, 150) // resizes the image to these dimensions (in pixel)\]
                .into(view)
        }
    }
}