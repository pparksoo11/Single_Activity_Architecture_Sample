package com.soo.sample_saa_project.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soo.domain.model.DomainGitRepoModel
import com.soo.sample_saa_project.base.BaseRecyclerViewAdapter
import com.soo.sample_saa_project.base.BaseRecyclerViewHolder
import com.soo.sample_saa_project.databinding.ItemRepoListBinding

class RepoAdapter(private val callback: RepoClickInterface) : BaseRecyclerViewAdapter<DomainGitRepoModel, RepoAdapter.RepoViewHolder>() {

    interface RepoClickInterface {
        fun clickRepo(url: String)
    }

    inner class RepoViewHolder(
        private val itemRepoListBinding: ItemRepoListBinding
    ) : BaseRecyclerViewHolder<DomainGitRepoModel>(itemRepoListBinding.root) {
        override fun bind(listener: View.OnClickListener, item: DomainGitRepoModel) {
            with(itemRepoListBinding) {
                clickListener = listener
                repo = item
            }
        }
    }

    override fun createItemViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun bindItemViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItemAt(position)
        item?.let {
            holder.apply {
                bind(createOnClickListener(it), item)
            }
        }
    }

    private fun createOnClickListener(item: DomainGitRepoModel): View.OnClickListener {
        return View.OnClickListener {
            callback.clickRepo(item.url)
        }
    }
}