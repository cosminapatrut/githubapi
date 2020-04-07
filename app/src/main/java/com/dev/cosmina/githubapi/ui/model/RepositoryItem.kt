package com.dev.cosmina.githubapi.ui.model

import com.dev.cosmina.githubapi.data.model.RepoDetailsDTO
import com.dev.cosmina.githubapi.data.model.RepoOwnerDTO

data class RepositoryItem (
    val name : String?,
    val description : String?,
    val stargazers_count : Int?,
    val language : String?,
    val forks_count : Int?,
    val watchers: Int?,
    val owner: RepoOwnerDTO
) {
    companion object {
        fun fromRepoApi(repoApi: RepoDetailsDTO): RepositoryItem {
            return RepositoryItem(
                repoApi.name,
                repoApi.description,
                repoApi.stargazers_count,
                repoApi.language,
                repoApi.forks_count,
                repoApi.watchers,
                repoApi.owner)
        }
    }
}