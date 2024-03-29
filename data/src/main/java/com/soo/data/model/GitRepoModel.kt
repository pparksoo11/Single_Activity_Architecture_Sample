package com.soo.data.model

import com.google.gson.annotations.SerializedName

data class GitRepoModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("created_at")
    val date: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("description")
    val description: String?
)
