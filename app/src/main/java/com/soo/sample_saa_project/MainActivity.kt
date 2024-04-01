package com.soo.sample_saa_project

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.soo.sample_saa_project.base.BaseActivity
import com.soo.sample_saa_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private var FINISH_INTERVAL_TIME = 2000L
    private var waitTime = 0L

    override fun init() {
        settingNav()
    }

    override fun observeViewModel() {
    }

    private fun settingNav() {
        binding.navigation.itemIconTintList = null
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.navigation.setupWithNavController(navController)
        navController.setGraph(R.navigation.navigation_graph)
    }

    override fun onBackPressed() {
        if (navHostFragment.childFragmentManager.backStackEntryCount == 0) { // 백 로그 체크
            var tempTime = System.currentTimeMillis();
            var intervalTime = tempTime - waitTime
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed()
            } else {
                waitTime = tempTime
                Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }
        super.onBackPressed()
    }
}