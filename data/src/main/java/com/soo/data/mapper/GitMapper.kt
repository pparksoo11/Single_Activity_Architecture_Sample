package com.soo.data.mapper

import com.soo.data.model.GitRepoModel
import com.soo.domain.model.DomainGitRepoModel

object GitMapper {
    fun mapperGitRepo(response: List<GitRepoModel>?): List<DomainGitRepoModel>? {
        return if(response != null) response.toDomain()
        else null
    }

    private fun List<GitRepoModel>.toDomain(): List<DomainGitRepoModel> {
        val domainGitRpoModel = mutableListOf<DomainGitRepoModel>()
        for(item in this) {
            val entity = DomainGitRepoModel(
                item.name,
                item.date,
                item.url,
                item.description
            )
            domainGitRpoModel.add(entity)
        }
        return domainGitRpoModel
    }
}