package com.soo.sample_saa_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.soo.domain.model.DomainGitRepoModel
import com.soo.domain.usecase.GetRepositoryUseCase
import com.soo.sample_saa_project.base.BaseViewModel
import com.soo.sample_saa_project.utils.ScreenState
import com.soo.sample_saa_project.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getRepositoryUseCase: GetRepositoryUseCase
): BaseViewModel() {

    val repoList: LiveData<List<DomainGitRepoModel>?> get() = _repoList
    private val _repoList = SingleLiveEvent<List<DomainGitRepoModel>?>()
    // git api 정보 가져오기
    fun getRepository(user: String) = viewModelScope.launch {
        mutableScreenState.postValue(ScreenState.LOADING) //로딩
        val response = getRepositoryUseCase.execute(this@RepositoryViewModel, user)
        if(response == null) {
            mutableScreenState.postValue(ScreenState.ERROR)
        } else {
            Log.d("pys : ", "$response")
            _repoList.postValue(response)
            mutableScreenState.postValue(ScreenState.RENDER)
        }
    }
}