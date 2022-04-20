package com.sammengistu.quic.ui.home.activites

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sammengistu.quic.R

class QuicWebActivity: AppCompatActivity() {

    companion object {
        private const val TAG = "QuicWebActivity"
        const val URL_EXTRA = "URL_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val myWebView: WebView = findViewById(R.id.webview)
        val url = intent.getStringExtra(URL_EXTRA)
        if (!url.isNullOrEmpty()) {
            myWebView.loadUrl(url)
        } else {
            Log.e(TAG, "Empty URL")
            finish()
        }
    }
}