package com.sammengistu.quic.ui.home.data


import com.sammengistu.quic.data.models.News
import com.sammengistu.quic.ui.home.CardViewType
import com.sammengistu.quic.utils.DateUtils

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
): CardViewAdapterItem {

    companion object {
        fun transform(news: News): List<ArticleUIItem> {
            val list = mutableListOf<ArticleUIItem>()
            news.articles?.let {
                for (article in it) {
                    list.add(
                        ArticleUIItem(
                            article.source?.name ?: "",
                            article.author ?: "",
                            article.title ?: "",
                            article.description ?: "",
                            article.url ?: "",
                            article.content ?: "",
                            article.urlToImage ?: "",
                            DateUtils.convertUTCDate(article.publishedAt)
                        )
                    )
                }
            }
            return list
        }
    }
}