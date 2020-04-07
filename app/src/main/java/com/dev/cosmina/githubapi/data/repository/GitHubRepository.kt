package com.dev.cosmina.githubapi.data.repository

import com.dev.cosmina.githubapi.data.model.RepoDetailsDTO
import com.dev.cosmina.githubapi.data.remote.api.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class GitHubRepository private constructor(private val api: ApiService) {

    companion object {

        @Volatile private var INSTANCE: GitHubRepository? = null

        fun get(api: ApiService): GitHubRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GitHubRepository(api).also { INSTANCE = it }
            }
        }
    }

    fun getAllRepositories(query: String, sort: String, order: String): Single<List<RepoDetailsDTO>> {
        return api.findRepos(query, sort, order)
            .subscribeOn(io())
            .map { it.items }
    }

}