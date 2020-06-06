package com.indo.news.di.module

import com.indo.news.modules.headline.HeadlineFrag
import com.indo.news.modules.home.HomeFrag
import com.indo.news.modules.politic.PoliticFrag
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
    internal abstract fun contributeHeadlineFrag(): HeadlineFrag

    @ContributesAndroidInjector
    internal abstract fun contributePoliticFrag(): PoliticFrag

    @ContributesAndroidInjector
    internal abstract fun contributeSportFrag(): SportFrag


}