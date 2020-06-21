package com.indo.news.modules.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.data.model.Article
import com.indo.news.modules.entertainment.adapter.EntertainmentAdapter
import com.indo.news.modules.home.adapter.viewholder.HomeVH
import com.indo.news.utils.extension.debug

class HomeAdapter : PagingDataAdapter<Article, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeVH.create(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || position % 3 == 0) HomeVH.NEWS_ITEM1
        else HomeVH.NEWS_ITEM2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            (holder as HomeVH).bind(article)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}