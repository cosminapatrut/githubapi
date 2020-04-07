package com.dev.cosmina.githubapi.ui.base

import androidx.lifecycle.ViewModel
import com.dev.cosmina.githubapi.ui.model.RepositoryItem
import com.dev.cosmina.githubapi.utils.network.NetworkHelper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    abstract var searchQuery: String

    abstract fun getReposSubject(): Observable<List<RepositoryItem>>

    abstract fun getLoadingIndicatorSubject(): Observable<Boolean>

    abstract fun gelAllRepositories()
    abstract fun clear()

}