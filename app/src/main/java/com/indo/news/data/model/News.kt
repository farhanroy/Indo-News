package com.indo.news.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class News (
    @SerializedName("status") val status: String,
    @SerializedName("totalResult") val totalResults: Long,
    @SerializedName("articles") val articles: List<Article>
)

@Parcelize
data class Article (
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String? = null
): Parcelable

@Parcelize
data class Source (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
): Parcelable
