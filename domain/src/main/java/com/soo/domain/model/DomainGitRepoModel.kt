package com.soo.domain.model

import com.google.gson.annotations.SerializedName

data class DomainGitRepoModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("created_at")
    val date: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("description")
    val description: String?
)
