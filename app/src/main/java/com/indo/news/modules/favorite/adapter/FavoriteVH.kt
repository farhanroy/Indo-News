package com.indo.news.modules.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indo.news.databinding.ItemNews1Binding
import com.indo.news.modules.favorite.view.FavoriteFragDirections
import com.indo.news.services.db.entity.Favorite
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.favoriteToArticle

class FavoriteVH (val binding: ItemNews1Binding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Favorite){
        binding.apply {
            tvTitle.text = item.title
            tvTime.text = TimeAgo.getTimeAgo(item.publishedAt)
            Glide.with(itemView.context).load(item.urlToImage).into(ivNews)
        }
        binding.root.setOnClickListener {
            val action =
                FavoriteFragDirections.actionFavoriteFragToDetailFrag(favoriteToArticle(item))
            it.findNavController().navigate(action)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FavoriteVH {
            val view = ItemNews1Binding.inflate(LayoutInflater.from(parent.context))
            return FavoriteVH(view)
        }
    }
}