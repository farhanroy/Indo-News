package com.indo.news.di.module

import com.indo.news.modules.MainAct
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainAct(): MainAct
}