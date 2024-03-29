package com.soo.sample_saa_project.view.fragment

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.soo.sample_saa_project.R
import com.soo.sample_saa_project.base.BaseFragment
import com.soo.sample_saa_project.databinding.FragmentIntroBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class IntroFragment: BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun initView() {
        super.initView()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        bottomNav?.visibility = View.GONE
        Log.d("pys check : ", "introFragment Init")

        // 2초 후에 HomeFragment로 이동 내일 여기부터 수정필요
        coroutineScope.launch {
            delay(2000L)
            navigateToHomeFragment()
        }
    }

    // intro to home 이동
    private fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_introFragment_to_homeFragment)
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        bottomNav?.visibility = View.VISIBLE

        coroutineScope.cancel()
    }

}