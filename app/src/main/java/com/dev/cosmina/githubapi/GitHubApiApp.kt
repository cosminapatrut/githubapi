package com.dev.cosmina.githubapi

import android.app.Application
import com.dev.cosmina.githubapi.di.component.ActivityComponent
import com.dev.cosmina.githubapi.di.component.ApplicationComponent
import com.dev.cosmina.githubapi.di.component.DaggerApplicationComponent
import com.dev.cosmina.githubapi.di.module.ActivityModule
import com.dev.cosmina.githubapi.di.module.ApplicationModule

class GitHubApiApp: Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent
        private var activityComponent: ActivityComponent? = null

        fun getApplicationComponent(): ApplicationComponent {
            return applicationComponent
        }

        fun getActivityComponent(): ActivityComponent {
            return activityComponent ?: getApplicationComponent().getActivityComponent(ActivityModule()).also {
                activityComponent = it
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}

