package com.soo.sample_saa_project.viewmodel

import android.content.pm.PackageInfo
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.lifecycle.LiveData
import com.soo.sample_saa_project.BuildConfig
import com.soo.sample_saa_project.base.BaseViewModel
import com.soo.sample_saa_project.base.BaseWebChromeClient
import com.soo.sample_saa_project.base.BaseWebViewClient
import com.soo.sample_saa_project.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(): BaseViewModel() {

    val horizontalProgress: LiveData<Boolean> get() = _horizontalProgress
    private val _horizontalProgress = SingleLiveEvent<Boolean>()

    val progressPercent: LiveData<Int> get() = _progressPercent
    private val _progressPercent = SingleLiveEvent<Int>()

    fun initWebView(webView: WebView) {
        webView.run {
            webViewClient = BaseWebViewClient(this@WebviewViewModel)
            webChromeClient = BaseWebChromeClient(this@WebviewViewModel)
            setLayerType(View.LAYER_TYPE_HARDWARE, null) // 웹뷰 성능 향상

            settings.run { // 세부 세팅 목록
                javaScriptEnabled = true // 웹 페이지 JS 호출 허용
                javaScriptCanOpenWindowsAutomatically = false // JS 새창 띄우기 허용 여부
                setSupportMultipleWindows(true) // 멀티 윈도우 사용 여부
                loadWithOverviewMode = true // 메타 태그 허용 여부
                useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
                setSupportZoom(false) // 줌 허용 여부
                builtInZoomControls = true // 화면 확대 축소 허용 여부
                domStorageEnabled = true // Dom 저장소 API 활성화(로컬 스토리지, 세션 스토리지 사용 여부 설정)
                databaseEnabled = true //DB storage API 사용 여부 설정
                isVerticalScrollBarEnabled = true // 세로 스크롤바 노출
                isHorizontalScrollBarEnabled = false // 가로 스크롤바 비노출
                cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW //https, http 호환 여부(https에서 http 컨텐츠 노출도 허용)
                displayZoomControls = false // webview의 +,- 줌 아이콘 노출여부
                pluginState = WebSettings.PluginState.ON // 플러그인 사용 설정
                CookieManager.getInstance().setAcceptCookie(true) //쿠키 활성화
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView,true) //제3자 쿠키 활성화

                if(BuildConfig.DEBUG) WebView.setWebContentsDebuggingEnabled(true) // webview 디버깅
            }
        }
    }

    fun showHorizontalProgress() {
        _horizontalProgress.postValue(true)
    }

    fun hideHorizontalProgress() {
        _horizontalProgress.postValue(false)
    }

    fun progressPercent(percent: Int) {
        _progressPercent.value = percent
    }
}