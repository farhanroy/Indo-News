package com.indo.news

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.indo.news.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("TimberInitializer is initialized.")
    }
}
