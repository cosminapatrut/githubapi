package com.dev.cosmina.githubapi.data.remote.api

import com.dev.cosmina.githubapi.data.model.RepoListDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun findRepos(@Query("q") query: String): Single<RepoListDTO>

}