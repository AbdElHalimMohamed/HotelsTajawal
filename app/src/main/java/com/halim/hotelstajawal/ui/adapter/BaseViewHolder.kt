package com.halim.hotelstajawal.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.halim.hotelstajawal.ui.dp2Px


abstract class BaseViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(model: T)

    fun resizeViewBasedOnScreenSize(view: View, spanCount: Int, marginDP: Int,
                                    height2WidthRation: Float): View {
        val displayMetrics = view.context.resources.displayMetrics
        val layoutParams = view.layoutParams
        val marginPix = view.context.dp2Px(marginDP.toFloat()).toInt()
        layoutParams.width = (displayMetrics.widthPixels - (marginPix * spanCount)) / spanCount
        layoutParams.height = (layoutParams.width * height2WidthRation).toInt()
        return view
    }
}