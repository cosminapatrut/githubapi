package com.dev.cosmina.githubapi.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import com.dev.cosmina.githubapi.data.repository.GitHubRepository
import com.dev.cosmina.githubapi.di.ActivityScope
import com.dev.cosmina.githubapi.ui.base.BaseViewModel
import com.dev.cosmina.githubapi.ui.model.RepositoryItem
import com.dev.cosmina.githubapi.utils.network.NetworkHelper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(private val repository: GitHubRepository,
                                        compositeDisposable: CompositeDisposable,
                                        networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, networkHelper) {

    override var searchQuery: String = "android"
    private val sort = "stars"
    private val order = "desc"
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

    override fun gelAllRepositories() {
        if(checkInternetConnection()){
            compositeDisposable.add(repository.getAllRepositories(searchQuery, sort, order)
                .observeOn(Schedulers.computation())
                .map {
                    it.map {
                            repoDb -> RepositoryItem.fromRepoApi(repoDb)
                    }
                }
                .doOnSubscribe { loadingIndicatorSubject.onNext(true) }
                .doFinally { loadingIndicatorSubject.onNext(false) }
                .subscribe(
                    { reposSubject.onNext(it) },
                    { Log.d(TAG, "Error loading items from the repository", it) }
                ))
        }
    }
}