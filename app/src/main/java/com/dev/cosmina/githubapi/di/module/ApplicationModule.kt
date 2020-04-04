package com.dev.cosmina.githubapi.di.module

import android.content.Context
import com.dev.cosmina.githubapi.GitHubApiApp
import com.dev.cosmina.githubapi.data.remote.api.ApiService
import com.dev.cosmina.githubapi.data.repository.GitHubRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module for app level dependencies.
 */
@Module
class ApplicationModule(application: GitHubApiApp) {

    private var applicationContext: Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return applicationContext
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "https://api.github.com"
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService): GitHubRepository {
        return GitHubRepository.get(apiService)
    }
}