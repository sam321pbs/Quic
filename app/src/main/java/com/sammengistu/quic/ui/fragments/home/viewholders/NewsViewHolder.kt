package com.sammengistu.quic.ui.fragments.home.viewholders

import com.sammengistu.quic.data.models.news.Article
import com.sammengistu.quic.data.models.news.News
import com.sammengistu.quic.databinding.ItemNewsBinding
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem

class NewsViewHolder(private val binding: ItemNewsBinding): BaseCardViewHolder(binding.root) {
    override fun bindView(item: CardViewAdapterItem) {
        binding.item = item as Article
        binding.executePendingBindings()
    }
}