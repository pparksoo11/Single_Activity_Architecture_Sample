package com.soo.sample_saa_project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * baseFragment를 상속받아 공통으로 사용될 메서드 추가 필요
 * */
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {

    protected lateinit var binding : T
        private set

    private var rootView: View? = null
    private var isFirstInit = true

    protected open fun initView() {}
    protected open fun observeViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if(rootView == null) {
            DataBindingUtil.inflate<T>(inflater, layoutId, container, false).apply {
                binding = this
                rootView = this.root
            }.root
        } else {
            rootView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner= viewLifecycleOwner
        if(isFirstInit) {
            isFirstInit = false
            initView()
            observeViewModel()
        }
    }
}