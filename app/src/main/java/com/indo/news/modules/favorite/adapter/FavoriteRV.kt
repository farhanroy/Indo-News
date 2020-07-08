package com.indo.news.modules.favorite.adapter

import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indo.news.services.db.entity.Favorite

class FavoriteRV : RecyclerView.Adapter<FavoriteVH>() {
    private var data = listOf<Favorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVH {
        return FavoriteVH.create(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FavoriteVH, position: Int) {
        holder.bind(data[position])
    }

    fun submitData(newItems: List<Favorite>) {
        val diffCallback = FavoriteDiffCallback(data, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}

class FavoriteDiffCallback(
    private val oldList: List<Favorite>,
    private val newList: List<Favorite>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean =
        oldList[oldPosition].id == newList[newPosition].id

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}