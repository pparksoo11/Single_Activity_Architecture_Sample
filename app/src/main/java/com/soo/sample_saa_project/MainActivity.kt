package com.soo.sample_saa_project

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
}