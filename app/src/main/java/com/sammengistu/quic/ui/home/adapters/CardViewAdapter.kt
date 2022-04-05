package com.sammengistu.quic.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sammengistu.quic.R
import com.sammengistu.quic.databinding.ItemWeatherBinding
import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.viewholders.ArticleViewHolder
import com.sammengistu.quic.ui.home.viewholders.BaseCardViewHolder
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
                        R.layout.item_weather,
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

            else -> WeatherViewHolder(ItemWeatherBinding.inflate(inflater))
        }
    }

    override fun onBindViewHolder(viewHolder: BaseCardViewHolder, position: Int) {
        dataSet[position]?.let {
            viewHolder.bindView(it)
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateList(dataSet: List<CardViewAdapterItem>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    fun addItem(item: CardViewAdapterItem) {
        dataSet.add(item)
        notifyDataSetChanged()
    }
}