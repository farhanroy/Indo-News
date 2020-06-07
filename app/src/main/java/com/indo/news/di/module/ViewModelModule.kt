package com.indo.news.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indo.news.di.ViewModelKey
import com.indo.news.modules.entertainment.viewmodel.EntertainmentVM
import com.indo.news.modules.home.viewmodel.HomeVM
import com.indo.news.modules.business.viewmodel.BusinessVM
import com.indo.news.modules.sport.viewmodel.SportVM
import com.indo.news.utils.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EntertainmentVM::class)
    abstract fun bindHeadlineVM(fragmentViewModel: EntertainmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    abstract fun bindHomeVM(fragmentViewModel: HomeVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BusinessVM::class)
    abstract fun bindPoliticVM(fragmentViewModel: BusinessVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SportVM::class)
    abstract fun bindSportVM(fragmentViewModel: SportVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}