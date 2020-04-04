package com.dev.cosmina.githubapi.di.module

import com.dev.cosmina.githubapi.ui.base.BaseViewModel
import com.dev.cosmina.githubapi.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */

@Module
class ActivityModule {

    @Provides
    fun provideHomeViewModel(homeViewModel: HomeViewModel): BaseViewModel {
        return homeViewModel
    }

}