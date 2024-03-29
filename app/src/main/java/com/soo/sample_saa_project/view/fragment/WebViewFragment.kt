package com.soo.sample_saa_project.view.fragment

import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.soo.sample_saa_project.R
import com.soo.sample_saa_project.base.BaseFragment
import com.soo.sample_saa_project.databinding.FragmentWebviewBinding
import com.soo.sample_saa_project.viewmodel.WebviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment: BaseFragment<FragmentWebviewBinding>(R.layout.fragment_webview) {

    // Safe Args를 통해 전달된 데이터 받기
    val args: WebViewFragmentArgs by navArgs()

    private val webviewViewModel by viewModels<WebviewViewModel>()

    override fun initView() {
        super.initView()

        // webview fragment에서는 bottom UI 제거
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        bottomNav?.visibility = View.GONE

        binding.vm = webviewViewModel
        val url = args.url // safe args url 받음

        webviewViewModel.initWebView(binding.webview) // webview 기본 셋팅
        binding.webview.loadUrl(url)

        // 뒤로가기 설정
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(binding.webview.canGoBack()) {
                binding.webview.goBack()
            } else {
                findNavController().popBackStack()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        bottomNav?.visibility = View.VISIBLE
    }
}