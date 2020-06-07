package com.indo.news.utils.extension

import java.text.SimpleDateFormat
import java.util.*

object TimeAgo {
    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    private fun setTimeToLong(publishedAt: String): Long {
        val format =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = format.parse(publishedAt)
        return date?.time!!
    }

    fun getTimeAgo(date: String): String? {
        var time = setTimeToLong(date)
        if (time < 1000000000000L) {
            time *= 1000
        }
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> {
                "just now"
            }
            diff < 2 * MINUTE_MILLIS -> {
                "a minute ago"
            }
            diff < 50 * MINUTE_MILLIS -> {
                "${diff / MINUTE_MILLIS} minutes ago"
            }
            diff < 90 * MINUTE_MILLIS -> {
                "an hour ago"
            }
            diff < 24 * HOUR_MILLIS -> {
                "${diff / HOUR_MILLIS} hours ago"
            }
            diff < 48 * HOUR_MILLIS -> {
                "yesterday"
            }
            else -> {
                "${diff / DAY_MILLIS} days ago"
            }
        }
    }
}