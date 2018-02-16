package com.halim.hotelstajawal.ui.adapter

import android.support.v7.widget.RecyclerView


abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(list: List<T>)
    : RecyclerView.Adapter<VH>() {

    val list: ArrayList<T> = ArrayList(list)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    open fun setItems(items: List<T>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    open fun addItems(items: List<T>) {
        val oldSize = list.size
        list.addAll(items)

        notifyItemRangeInserted(oldSize, list.size - oldSize)
    }

    override fun getItemCount(): Int = list.size
}