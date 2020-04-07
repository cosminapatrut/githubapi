package com.dev.cosmina.githubapi.di.component

import com.dev.cosmina.githubapi.di.ActivityScope
import com.dev.cosmina.githubapi.di.module.ActivityModule
import com.dev.cosmina.githubapi.ui.home.HomeActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: HomeActivity)

}