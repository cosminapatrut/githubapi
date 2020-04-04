package com.dev.cosmina.githubapi.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for converting Json response.
 */
data class RepoLicenseDTO (

    @SerializedName("key") val key : String,
    @SerializedName("name") val name : String,
    @SerializedName("spdx_id") val spdx_id : String,
    @SerializedName("url") val url : String,
    @SerializedName("node_id") val node_id : String

)