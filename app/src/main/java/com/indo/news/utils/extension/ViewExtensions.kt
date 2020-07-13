package com.indo.news.utils.extension

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.indo.news.data.model.Article
import com.indo.news.services.db.entity.Favorite


fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T : ViewDataBinding?> AppCompatActivity.setActBinding(layoutId: Int): T {
    return DataBindingUtil.setContentView<T>(this, layoutId)
}

fun <T : ViewDataBinding?> Fragment.setFragBinding(layoutId: Int, container: ViewGroup?): T {
    return DataBindingUtil.inflate<T>(layoutInflater, layoutId, container, false)
}

fun <T : ViewDataBinding?> View.setLayoutBinding(layoutId: Int, container: ViewGroup?): T {
    return DataBindingUtil.inflate<T>(
        LayoutInflater.from(context),
        layoutId, container, false
    )
}

fun toVisibility(constraint: Boolean): Int = if (constraint) {
    View.VISIBLE
} else {
    View.GONE
}

fun AppCompatActivity.context(): Context {
    return this
}

fun Any?.isNull(): Boolean = this == null

fun tintMenuIcon(item: MenuItem) {
    val itemIcon: Drawable = item.icon
    val iconWrapper = DrawableCompat.wrap(itemIcon)
    item.icon = iconWrapper
}

fun articleToFavorite(article: Article): Favorite {
    return Favorite(
        author = article.author,
        title = article.title,
        description = article.description,
        url = article.url,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content
    )
}
