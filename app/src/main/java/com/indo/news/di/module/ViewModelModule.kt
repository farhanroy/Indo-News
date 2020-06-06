package com.indo.news.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indo.news.di.ViewModelKey
import com.indo.news.modules.headline.viewmodel.HeadlineVM
import com.indo.news.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HeadlineVM::class)
    abstract fun bindHeadlineVM(fragmentViewModel: HeadlineVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}