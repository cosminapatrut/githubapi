package com.dev.cosmina.githubapi.data.repository

import com.dev.cosmina.githubapi.data.model.RepoDetailsDTO
import com.dev.cosmina.githubapi.data.remote.api.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GitHubRepository private constructor(private val api: ApiService) {

    companion object {

        // Repository singleton instance.
        @Volatile private var INSTANCE: GitHubRepository? = null

        /**
         * Provides repository instance.
         *
         * @param api service to provide remote data.
         */
        fun get(api: ApiService): GitHubRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GitHubRepository(api).also { INSTANCE = it }
            }
        }
    }

    fun getAllRepositories(query: String): Single<List<RepoDetailsDTO>> {
        return api.findRepos(query)
            .subscribeOn(Schedulers.io())
            .map { it.items }
    }


}