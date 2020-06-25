package com.indo.news.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.indo.news.services.db.entity.Article
import kotlinx.android.parcel.Parcelize

data class News (
    @SerializedName("status") val status: String,
    @SerializedName("totalResult") val totalResults: Long,
    @SerializedName("articles") val articles: List<Article>
)

@Parcelize
data class Source (
    @SerializedName("id") val sourceId: String,
    @SerializedName("name") val name: String
): Parcelable
