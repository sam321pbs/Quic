package com.sammengistu.quic.ui.home.handlers

import android.content.Context
import android.content.Intent
import com.sammengistu.quic.ui.home.activites.QuicWebActivity
import com.sammengistu.quic.ui.home.data.uiitem.ArticleUIItem

class WebviewHandler {

    fun onClickReadMore(context: Context, item: ArticleUIItem) {
        val intent = Intent(context, QuicWebActivity::class.java)
        intent.putExtra(QuicWebActivity.URL_EXTRA, item.url)
        context.startActivity(intent)
    }
}