package com.indo.news.modules.entertainment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.R
import com.indo.news.data.model.Article
import com.indo.news.data.model.News
import com.indo.news.databinding.ItemNews1Binding
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.setLayoutBinding

class EntertainmentAdapter (
    private val listNews: News,
    private val clickListener: (Article) -> Unit
): RecyclerView.Adapter<EntertainmentAdapter.HeadLineVH>() {

    private lateinit var binding: ItemNews1Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLineVH {
        binding = parent.setLayoutBinding(R.layout.item_news1, parent)
        return HeadLineVH(binding)
    }

    override fun getItemCount(): Int = listNews.articles.size

    override fun onBindViewHolder(holder: HeadLineVH, position: Int) {
        val item = listNews.articles[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener(item) }
    }

    inner class HeadLineVH(binding: ItemNews1Binding): RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article){
            binding.data = article
            binding.time = TimeAgo.getTimeAgo(article.publishedAt)
        }
    }
}