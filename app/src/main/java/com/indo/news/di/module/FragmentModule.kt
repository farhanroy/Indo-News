package com.indo.news.di.module

import com.indo.news.modules.entertainment.EntertainmentFrag
import com.indo.news.modules.home.HomeFrag
import com.indo.news.modules.business.BusinessFrag
import com.indo.news.modules.detail.DetailFrag
import com.indo.news.modules.splash.SplashFrag
import com.indo.news.modules.sport.SportFrag
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeSplashFrag(): SplashFrag

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFrag(): HomeFrag

    @ContributesAndroidInjector
    internal abstract fun contributeHeadlineFrag(): EntertainmentFrag

    @ContributesAndroidInjector
    internal abstract fun contributePoliticFrag(): BusinessFrag

    @ContributesAndroidInjector
    internal abstract fun contributeSportFrag(): SportFrag

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFrag(): DetailFrag
}