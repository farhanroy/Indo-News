package com.indo.news.utils.constant

import com.indo.news.BuildConfig

class Constants {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val API_KEY = BuildConfig.API_KEY
        const val CACHE_SIZE: Long = 5 * 1024 * 1024
        const val TO_DETAIL = "to_detail"
    }
}