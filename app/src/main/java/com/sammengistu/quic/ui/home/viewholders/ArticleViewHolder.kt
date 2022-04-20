package com.sammengistu.quic.ui.home.viewholders

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sammengistu.quic.databinding.ItemArticleBinding
import com.sammengistu.quic.ui.home.data.ArticleUIItem
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.handlers.WebviewHandler

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : BaseCardViewHolder(binding.root) {

    override fun bindView(item: CardViewAdapterItem) {
        binding.item = item as ArticleUIItem
        binding.handler = WebviewHandler()
        binding.context = binding.root.context
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide.with(view.context)
                .load(imageUrl)
                .override(300, 300) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(view)
        }
    }
}