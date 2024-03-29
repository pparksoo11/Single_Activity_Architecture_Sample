package com.soo.sample_saa_project.base

import android.os.Message
import android.webkit.GeolocationPermissions
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.soo.sample_saa_project.viewmodel.WebviewViewModel
import javax.inject.Inject

class BaseWebChromeClient @Inject constructor(private val viewModel: WebviewViewModel): WebChromeClient() {
    // webview에서 loaction 권한
    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
        callback?.invoke(origin, true, true)
    }

    // 새창처리 로직 정리
    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    // 새창 닫기
    override fun onCloseWindow(window: WebView?) {
        super.onCloseWindow(window)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        viewModel.progressPercent(newProgress)
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }
}