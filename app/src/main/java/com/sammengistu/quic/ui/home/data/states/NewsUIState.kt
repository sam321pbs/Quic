package com.sammengistu.quic.ui.home.data.states

import com.sammengistu.quic.ui.home.data.uiitem.ArticleUIItem

sealed class NewsUIState {
    data class Success(val news: List<ArticleUIItem>): NewsUIState()
    data class Error(val exception: Throwable): NewsUIState()
}
