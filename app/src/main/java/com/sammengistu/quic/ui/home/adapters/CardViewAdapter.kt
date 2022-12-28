package com.sammengistu.quic.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sammengistu.quic.R
import com.sammengistu.quic.databinding.ItemWeather2Binding
import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.viewholders.ArticleViewHolder
import com.sammengistu.quic.ui.home.viewholders.BaseCardViewHolder
import com.sammengistu.quic.ui.home.viewholders.MarketViewHolder
import com.sammengistu.quic.ui.home.viewholders.WeatherViewHolder

class CardViewAdapter: RecyclerView.Adapter<BaseCardViewHolder>() {

    private val dataSet: MutableList<CardViewAdapterItem?> = mutableListOf()

    override fun getItemViewType(position: Int) = dataSet[position]?.cardViewType?.ordinal ?: 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseCardViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return when (viewType) {
            CardViewType.WEATHER.ordinal ->
                WeatherViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_weather_2,
                        viewGroup,
                    false
                    )
                )
            CardViewType.ARTICLE.ordinal ->
                ArticleViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_article,
                        viewGroup,
                        false
                    )
                )
            CardViewType.MARKET.ordinal ->
                MarketViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_market,
                        viewGroup,
                        false
                    )
                )
            else -> WeatherViewHolder(ItemWeather2Binding.inflate(inflater))
        }
    }

    override fun onBindViewHolder(viewHolder: BaseCardViewHolder, position: Int) {
        dataSet[position]?.let {
            viewHolder.bindView(it)
        }
    }

    override fun getItemCount() = dataSet.size

    fun addList(items: List<CardViewAdapterItem>) {
        dataSet.clear()
        dataSet.addAll(items)
        dataSet.sortBy { it?.cardViewType?.ordinal }
        notifyDataSetChanged()
    }
}