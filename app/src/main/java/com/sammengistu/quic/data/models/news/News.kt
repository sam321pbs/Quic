package com.sammengistu.quic.data.models.news

import com.sammengistu.quic.ui.fragments.home.CardViewType
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem

data class News(
    val status: String?,
    val articles: List<Article>?
): CardViewAdapterItem {

    override val cardViewType: CardViewType
        get() = CardViewType.NEWS
}

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val content: String?,
    val urlToImage: String?
): CardViewAdapterItem {

    override val cardViewType: CardViewType
        get() = CardViewType.NEWS
}

data class Source(
    val id: String?,
    val name: String?
)
