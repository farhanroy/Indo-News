package com.indo.news.modules.home.adapter.recyclerview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.modules.home.adapter.viewholder.NewsCategoryVH
import com.indo.news.services.db.entity.Article

class NewsCategoryRV : PagingDataAdapter<Article, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsCategoryVH.create(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || position % 3 == 0) NewsCategoryVH.NEWS_ITEM1
        else NewsCategoryVH.NEWS_ITEM2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            (holder as NewsCategoryVH).bind(article)
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