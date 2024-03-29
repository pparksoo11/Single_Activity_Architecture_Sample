package com.soo.sample_saa_project.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soo.domain.utils.ErrorType
import com.soo.domain.utils.RemoteErrorEmitter
import com.soo.sample_saa_project.utils.ScreenState
import com.soo.sample_saa_project.utils.SingleLiveEvent

/**
 * BaseViewModel : 에러, 로딩, 화면 상태 등 공통적으로 사용되는 내용 정리
 * */
abstract class BaseViewModel : ViewModel(), RemoteErrorEmitter {

    val mutableProgress = MutableLiveData<Int>(View.GONE)
    val mutableScreenState = SingleLiveEvent<ScreenState>() //화면 상태 표시
    val mutableErrorMessage = SingleLiveEvent<String>()
    val mutableSuccessMessage = MutableLiveData<String>()
    val mutableErrorType = SingleLiveEvent<ErrorType>() //에러 타입(세션,네트워크 등등)

    override fun onError(msg: String) {
        mutableErrorMessage.postValue(msg)
    }

    override fun onError(errorType: ErrorType) {
        mutableErrorType.postValue(errorType)
    }
}