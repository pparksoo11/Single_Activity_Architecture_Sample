package com.soo.data.di

import com.soo.data.repository.remote.GitRepositoryImpl
import com.soo.data.repository.remote.datasourceimpl.GitDataSourceImpl
import com.soo.domain.repository.remote.GitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGitRepository(
        gitDataSourceImpl: GitDataSourceImpl
    ): GitRepository {
        return GitRepositoryImpl(gitDataSourceImpl)
    }
}