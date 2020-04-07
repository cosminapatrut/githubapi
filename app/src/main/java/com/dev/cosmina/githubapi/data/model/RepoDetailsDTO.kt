package com.dev.cosmina.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class RepoDetailsDTO (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("full_name") val full_name : String,
    @SerializedName("owner") val owner : RepoOwnerDTO,
    @SerializedName("description") val description : String,
    @SerializedName("fork") val fork : Boolean,
    @SerializedName("stargazers_count") val stargazers_count : Int,
    @SerializedName("watchers_count") val watchers_count : Int,
    @SerializedName("language") val language : String,
    @SerializedName("forks_count") val forks_count : Int,
    @SerializedName("forks") val forks : Int,
    @SerializedName("watchers") val watchers : Int

)