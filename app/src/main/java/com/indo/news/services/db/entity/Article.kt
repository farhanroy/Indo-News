package com.indo.news.services.db.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.indo.news.data.model.Source
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "article_table")
data class Article (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @Embedded
    @field:SerializedName("source") val source: Source,
    @field:SerializedName("author") val author: String? = null,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("urlToImage") val urlToImage: String,
    @field:SerializedName("publishedAt") val publishedAt: String,
    @field:SerializedName("content") val content: String? = null
): Parcelable