package com.sammengistu.quic.ui.home.data.transformers

import com.sammengistu.quic.networking.data.models.Article
import com.sammengistu.quic.networking.data.models.CurrentWeather
import com.sammengistu.quic.networking.data.models.Market
import com.sammengistu.quic.ui.home.data.uiitem.ArticleUIItem
import com.sammengistu.quic.ui.home.data.uiitem.MarketUIItem
import com.sammengistu.quic.ui.home.data.uiitem.WeatherUIItem
import com.sammengistu.quic.utils.DateUtils

fun List<Article>.transformArticlesToUiItem(): List<ArticleUIItem> = this.map { article ->
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
}

fun List<Market>.transformMarketToUiItem(): List<MarketUIItem> = this.map { market ->
    MarketUIItem(
        market.shortName ?: "",
        market.exchange ?: "",
        market.regularMarketChange?.fmt ?: "",
        market.regularMarketChangePercent?.fmt ?: "",
        market.regularMarketPreviousClose?.fmt ?: "",
        market.regularMarketPrice?.fmt ?: "",
    )
}

fun CurrentWeather.transformWeatherToUiItem(): WeatherUIItem =
    WeatherUIItem(
        this.name,
        this.main.temp.toString(),
        this.main.temp_max.toString(),
        this.main.temp_min.toString(),
        this.main.humidity.toString(),
        this.weather[0]?.main ?: "",
        this.weather[0]?.description ?: "",
        this.weather[0]?.icon?.let {
            "https://openweathermap.org/img/wn/${this.weather[0]?.icon}@2x.png"
        } ?: run { "" }
    )