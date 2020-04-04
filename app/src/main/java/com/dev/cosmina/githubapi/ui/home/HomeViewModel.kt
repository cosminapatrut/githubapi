package com.dev.cosmina.githubapi.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.dev.cosmina.githubapi.data.repository.GitHubRepository
import com.dev.cosmina.githubapi.ui.base.BaseViewModel
import com.dev.cosmina.githubapi.ui.model.RepositoryItem
import com.dev.cosmina.githubapi.utils.network.NetworkHelper
import com.dev.cosmina.githubapi.utils.rx.SchedulerProvider
import com.mindorks.bootcamp.instagram.di.ActivityScope
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
@ActivityScope

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var searchQuery: String = ""

    private val reposSubject = BehaviorSubject.create<List<RepositoryItem>>()
    private val loadingIndicatorSubject = BehaviorSubject.createDefault(false)

    override fun getReposSubject(): Observable<List<RepositoryItem>> {
        return reposSubject
    }

    override fun getLoadingIndicatorSubject(): Observable<Boolean> {
        return loadingIndicatorSubject
    }

    override fun clear() {
        compositeDisposable.dispose()
    }

}