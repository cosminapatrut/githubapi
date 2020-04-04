package com.dev.cosmina.githubapi.di.component

import com.dev.cosmina.githubapi.di.module.ActivityModule
import com.dev.cosmina.githubapi.ui.home.HomeActivity
import com.mindorks.bootcamp.instagram.di.ActivityScope
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: HomeActivity)

}