package com.soo.domain.repository.remote

import com.soo.domain.model.DomainGitRepoModel
import com.soo.domain.utils.RemoteErrorEmitter

interface GitRepository {
    suspend fun getRepository(remoteErrorEmitter: RemoteErrorEmitter, user: String): List<DomainGitRepoModel>?
}