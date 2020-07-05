package com.indo.news.modules.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.data.model.News
import com.indo.news.utils.constant.Constants
import com.indo.news.utils.extension.isNull

class RecommendedRV(private val listNews: News) : RecyclerView.Adapter<RecommendedVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedVH {
        return RecommendedVH.create(parent)
    }

    override fun getItemCount(): Int = Constants.RECOMMENDED_SIZE

    override fun onBindViewHolder(holder: RecommendedVH, position: Int) {
        val article = listNews.articles[position]
        if (!article.isNull()) {
            holder.bind(article)
        }
    }
}