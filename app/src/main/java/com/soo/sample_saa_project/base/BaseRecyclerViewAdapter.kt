package com.soo.sample_saa_project.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseRecyclerViewAdapter, 공통으로 사용되는 메서드 정리
 * */
abstract class BaseRecyclerViewAdapter<S, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    //리스트
    var items = mutableListOf<S>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return createItemViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        bindItemViewHolder(holder, position)
    }

    /**
     * 아이템 개수
     * */
    override fun getItemCount() = items.size

    protected abstract fun createItemViewHolder(parent: ViewGroup, viewType: Int) : T
    protected abstract fun bindItemViewHolder(holder: T, position: Int)

    /**
     * 아이템 리스트 추가
     * @param items
     * @param clearPreviousItems
     */
    fun addAll(items: ArrayList<S>, clearPreviousItems: Boolean = false) {
        if (clearPreviousItems) {
            this.items.clear()
        }

        this.items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * 아이템 추가
     * @param item
     * @param position
     * @param clearPreviousItems
     */
    fun addItem(item: S, position: Int = items.size, clearPreviousItems: Boolean = false) {
        var adapterPosition = position
        if (clearPreviousItems) {
            this.items.clear()
            adapterPosition = 0
        }

        this.items.add(adapterPosition, item)
        notifyDataSetChanged()
    }

    /**
     * 아이템 삭제
     * @param position
     */
    fun removeItem(position: Int) {
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * 아이템 취득
     * @param position
     * @return
     */
    fun getItemAt(position: Int): S? {
        return items.elementAt(position)
    }

    /**
     * 아이템 변경
     * @param position
     * @param item
     */
    fun updateItemAt(position: Int, item: S) {
        if (position != -1) {
            items[position] = item
            notifyDataSetChanged()
        }
    }

    /**
     * 리스트 클리어
     */
    fun clearAdapter() {
        items.clear()
        notifyDataSetChanged()
    }
}