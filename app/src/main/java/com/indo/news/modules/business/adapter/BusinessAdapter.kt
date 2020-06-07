package com.indo.news.modules.business.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.R
import com.indo.news.data.model.Article
import com.indo.news.data.model.News
import com.indo.news.databinding.ItemNews1Binding
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.setLayoutBinding

class BusinessAdapter(
    private val listNews: News,
    private val clickListener: (Article) -> Unit
): RecyclerView.Adapter<BusinessAdapter.PoliticVH>() {

    private lateinit var binding: ItemNews1Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoliticVH {
        binding = parent.setLayoutBinding(R.layout.item_news1, parent)
        return PoliticVH(binding)
    }

    override fun getItemCount(): Int = listNews.articles.size

    override fun onBindViewHolder(holder: PoliticVH, position: Int) {
        val item = listNews.articles[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener(item) }
    }

    inner class PoliticVH(binding: ItemNews1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.data = article
            binding.time = TimeAgo.getTimeAgo(article.publishedAt)
        }
    }
}