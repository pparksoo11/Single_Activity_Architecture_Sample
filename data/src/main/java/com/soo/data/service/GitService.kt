package com.soo.data.service

import com.soo.data.model.GitRepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("users/{user}/repos")
    suspend fun getRepository(@Path("user") user: String): Response<List<GitRepoModel>>
}