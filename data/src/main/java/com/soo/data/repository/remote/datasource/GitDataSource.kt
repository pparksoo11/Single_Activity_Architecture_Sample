package com.soo.data.repository.remote.datasource

import com.soo.data.model.GitRepoModel
import com.soo.domain.utils.RemoteErrorEmitter

interface GitDataSource {
    suspend fun getRepository(remoteErrorEmitter: RemoteErrorEmitter, user: String): List<GitRepoModel>?
}