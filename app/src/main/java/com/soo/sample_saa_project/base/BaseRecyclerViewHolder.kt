package com.soo.sample_saa_project.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseRecyclerViewHolder
 * */
abstract class BaseRecyclerViewHolder<T>(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {
    abstract fun bind(listener: View.OnClickListener, item : T)
}