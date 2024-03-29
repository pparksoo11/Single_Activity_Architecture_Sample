package com.soo.domain.usecase

import com.soo.domain.repository.remote.GitRepository
import com.soo.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GetRepositoryUseCase @Inject constructor(
    private val gitRepository: GitRepository
){
    suspend fun execute(remoteErrorEmitter: RemoteErrorEmitter, user: String) = gitRepository.getRepository(remoteErrorEmitter, user)
}