package com.indo.news.modules.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indo.news.R
import com.indo.news.data.model.Article
import com.indo.news.modules.home.HomeFragDirections
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.isNull

class HomeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
    private val tvSource = itemView.findViewById<TextView>(R.id.tv_source)
    private val tvTime = itemView.findViewById<TextView>(R.id.tv_time)
    private val ivNews = itemView.findViewById<AppCompatImageView>(R.id.iv_news)

    private var article: Article? = null

    init {
        toDetail()
    }

    fun bind(article: Article) {
        if (!article.isNull()) {
            showData(article)
        }
    }

    private fun showData(article: Article) {
        this.article = article
        tvTitle.text = article.title
        tvSource.text = article.source.name
        tvTime.text = TimeAgo.getTimeAgo(article.publishedAt)

        Glide.with(itemView.context).load(article.urlToImage).into(ivNews)
    }

    private fun toDetail() {
        itemView.setOnClickListener {
            article?.let { article ->
                val action = HomeFragDirections.actionHomeFragToDetailFrag(article)
                it.findNavController().navigate(action)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): HomeVH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news1, parent, false)
            return HomeVH(view)
        }
    }
}