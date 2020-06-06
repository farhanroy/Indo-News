package com.indo.news.di.component

import android.app.Application
import com.indo.news.App
import com.indo.news.di.module.ActivityModule
import com.indo.news.di.module.NetworkModule
import com.indo.news.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}