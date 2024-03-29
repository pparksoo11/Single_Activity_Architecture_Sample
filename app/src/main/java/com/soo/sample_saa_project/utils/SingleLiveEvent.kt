package com.soo.sample_saa_project.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 특정 이벤트 발생 시 불필요한 Observer Event를 막기 위해 사용
 * */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    companion object {
        private const val TAG = "SingleLiveEvent"
    }

    // AtomicBoolean은 멀티 스레딩 환경에서 동시성을 보장 (false로 초기화)
    val mPending: AtomicBoolean = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if(hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner) { t ->
            if (mPending.compareAndSet(true, false)) { //mPending 값이 true일 때 조건문 처리 및 처리 후 false로 값 변경
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}