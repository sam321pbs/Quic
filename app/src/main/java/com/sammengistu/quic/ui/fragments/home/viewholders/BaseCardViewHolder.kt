package com.sammengistu.quic.ui.fragments.home.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem

abstract class BaseCardViewHolder(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bindView(item: CardViewAdapterItem)
}