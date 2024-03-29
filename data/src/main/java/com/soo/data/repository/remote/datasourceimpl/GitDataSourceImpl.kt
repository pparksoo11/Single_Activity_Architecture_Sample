package com.soo.data.repository.remote.datasourceimpl

import com.soo.data.model.GitRepoModel
import com.soo.data.repository.remote.datasource.GitDataSource
import com.soo.data.service.GitService
import com.soo.data.utils.BaseRepository
import com.soo.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GitDataSourceImpl @Inject constructor(
    private val gitService: GitService
): BaseRepository(), GitDataSource {
    override suspend fun getRepository(remoteErrorEmitter: RemoteErrorEmitter, user: String): List<GitRepoModel>? {
        return safeApiCall(remoteErrorEmitter) {
            gitService.getRepository(user).body()
        }
    }

}