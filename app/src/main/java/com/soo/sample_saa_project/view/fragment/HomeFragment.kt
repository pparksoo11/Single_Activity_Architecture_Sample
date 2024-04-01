package com.soo.sample_saa_project.view.fragment

import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soo.domain.model.DomainGitRepoModel
import com.soo.sample_saa_project.R
import com.soo.sample_saa_project.base.BaseFragment
import com.soo.sample_saa_project.databinding.FragmentHomeBinding
import com.soo.sample_saa_project.utils.ScreenState
import com.soo.sample_saa_project.view.adapter.RepoAdapter
import com.soo.sample_saa_project.viewmodel.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val repositoryViewModel by viewModels<RepositoryViewModel>()

    private var backPressedTime: Long = 0

    private val repoClickInterface = object : RepoAdapter.RepoClickInterface {
        override fun clickRepo(url: String) {
            val action = HomeFragmentDirections.actionHomeFragmentToWebViewFragment(url) // webfragment로 이동 및 url 전달
            findNavController().navigate(action)
        }
    }

    private val repoAdapter by lazy { RepoAdapter(repoClickInterface) }

    override fun initView() {
        super.initView()

        settingRepoRecyclerView()

        repositoryViewModel.getRepository("pparksoo11") //git user Name
    }

    override fun observeViewModel() {
        super.observeViewModel()

        repositoryViewModel.mutableScreenState.observe(this) {
            when(it) {
                ScreenState.LOADING -> { // 로딩
                    binding.progress.visibility = View.VISIBLE
                }
                ScreenState.RENDER -> { // 화면 정상 노출
                    binding.progress.visibility = View.GONE
                }
                ScreenState.ERROR -> { // 에러 화면 노출
                    binding.progress.visibility = View.GONE
                }
                else -> { // 알수 없는 에러(에러 화면 노출 동일)
                    binding.progress.visibility = View.GONE
                }
            }
        }

        // get repoList
        repositoryViewModel.repoList.observe(this) {
            repoAdapter.addAll(it as ArrayList<DomainGitRepoModel>)
        }
    }

    private fun settingRepoRecyclerView() {
        binding.recyclerView.apply {
            adapter = repoAdapter
            animation = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}