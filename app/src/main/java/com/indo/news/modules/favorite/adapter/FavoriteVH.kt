package com.indo.news.modules.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indo.news.databinding.ItemNews1Binding
import com.indo.news.services.db.entity.Favorite
import com.indo.news.utils.extension.TimeAgo

class FavoriteVH (val binding: ItemNews1Binding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Favorite){
        binding.apply {
            tvTitle.text = item.title
            tvTime.text = TimeAgo.getTimeAgo(item.publishedAt)

            Glide.with(itemView.context).load(item.urlToImage).into(ivNews)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FavoriteVH {
            val view = ItemNews1Binding.inflate(LayoutInflater.from(parent.context))
            return FavoriteVH(view)
        }
    }
}