package com.indo.news.modules.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indo.news.data.model.Article
import com.indo.news.databinding.ItemNews1Binding
import com.indo.news.utils.extension.TimeAgo

class RecommendedVH(private val binding: ItemNews1Binding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.apply {
            tvTitle.text = article.title
            tvSource.text = article.source.name
            tvTime.text = TimeAgo.getTimeAgo(article.publishedAt)
            Glide.with(itemView.context).load(article.urlToImage).into(ivNews)
        }
    }
    companion object {
        fun create(parent: ViewGroup): RecommendedVH {
            val view = ItemNews1Binding.inflate(LayoutInflater.from(parent.context))
            return RecommendedVH(view)
        }
    }
}