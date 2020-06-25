package com.indo.news.modules.home.adapter.recyclerview

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.indo.news.modules.home.adapter.viewholder.ItemLoadMoreVH


class ItemLoadMoreRV(private val retry: () -> Unit) : LoadStateAdapter<ItemLoadMoreVH>() {

    override fun onBindViewHolder(holder: ItemLoadMoreVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemLoadMoreVH {
        return ItemLoadMoreVH.create(parent, retry)
    }
}