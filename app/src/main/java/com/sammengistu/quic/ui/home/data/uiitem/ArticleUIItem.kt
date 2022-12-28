package com.sammengistu.quic.ui.home.data.uiitem

import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem

data class ArticleUIItem(
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val content: String,
    val urlToImage: String,
    val publishedAt: String,
    override val cardViewType: CardViewType = CardViewType.ARTICLE
): CardViewAdapterItem