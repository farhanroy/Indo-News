package com.indo.news.data.model

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("status") val status: String,
    @SerializedName("totalResult") val totalResults: Long,
    @SerializedName("articles") val articles: List<Article>
)

data class Article (
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String? = null
)

data class Source (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
