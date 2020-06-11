package com.indo.news.modules.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.R
import com.indo.news.databinding.ItemLoadMoreBinding
import com.indo.news.utils.extension.toVisibility

class ItemLoadMoreVH(
    private val binding: ItemLoadMoreBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.also {
            it.setOnClickListener { retry.invoke() }
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvError.text = loadState.error.localizedMessage
        }
        binding.loadingAnim.visibility = toVisibility(loadState is LoadState.Loading)
        binding.btnRetry.visibility = toVisibility(loadState !is LoadState.Loading)
        binding.tvError.visibility = toVisibility(loadState !is LoadState.Loading)
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ItemLoadMoreVH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_more, parent, false)
            val binding = ItemLoadMoreBinding.bind(view)
            return ItemLoadMoreVH(binding, retry)
        }
    }
}