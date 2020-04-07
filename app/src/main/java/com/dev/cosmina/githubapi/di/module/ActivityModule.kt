package com.dev.cosmina.githubapi.di.module

import com.dev.cosmina.githubapi.ui.base.BaseViewModel
import com.dev.cosmina.githubapi.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideHomeViewModel(homeViewModel: HomeViewModel): BaseViewModel {
        return homeViewModel
    }

}