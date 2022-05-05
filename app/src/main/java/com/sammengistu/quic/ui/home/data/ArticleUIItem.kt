package com.sammengistu.quic.ui.home.data

import com.sammengistu.quic.ui.home.CardViewType

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