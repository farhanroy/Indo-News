package com.indo.news.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("app:imageUrl")
fun setNewsImage(view: AppCompatImageView, imageUrl: String?) {
    val requestOptions = RequestOptions().centerCrop()
    Glide.with(view.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(view)
}