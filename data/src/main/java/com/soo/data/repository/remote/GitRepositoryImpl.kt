package com.soo.data.repository.remote

import com.soo.data.mapper.GitMapper
import com.soo.data.repository.remote.datasource.GitDataSource
import com.soo.domain.model.DomainGitRepoModel
import com.soo.domain.repository.remote.GitRepository
import com.soo.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val gitDataSource: GitDataSource
): GitRepository {
    override suspend fun getRepository(
        remoteErrorEmitter: RemoteErrorEmitter,
        user: String
    ): List<DomainGitRepoModel>? {
        return GitMapper.mapperGitRepo(gitDataSource.getRepository(remoteErrorEmitter, user))
    }
}