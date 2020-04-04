package com.dev.cosmina.githubapi.di.component


import com.dev.cosmina.githubapi.di.module.ActivityModule
import com.dev.cosmina.githubapi.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getActivityComponent(activityModule: ActivityModule): ActivityComponent

}
