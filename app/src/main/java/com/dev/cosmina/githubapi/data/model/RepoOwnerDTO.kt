package com.dev.cosmina.githubapi.data.model

import com.google.gson.annotations.SerializedName

data class RepoOwnerDTO (

    @SerializedName("login") val login : String

)