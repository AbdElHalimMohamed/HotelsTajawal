package com.halim.hotelstajawal.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.getBoundEntity
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.presenter.events.navigation.ToHotelDetailsEvent
import com.halim.hotelstajawal.domain.setBoundEntity
import com.halim.hotelstajawal.ui.getDarkestVibrantColor
import com.halim.hotelstajawal.ui.loadUrl
import kotlinx.android.synthetic.main.adapter_hotel_item.view.*


class HotelsAdapter(private val bus: Bus,
                    private val spanCount: Int,
                    private val marginDP: Int)
    : BaseRecyclerAdapter<Hotel, HotelsAdapter.HotelVM>(arrayListOf()) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HotelVM =
            HotelVM(spanCount, marginDP, bus, LayoutInflater.from(parent?.context)
                    .inflate(R.layout.adapter_hotel_item, parent, false))

    class HotelVM(private val spanCount: Int, private val marginDP: Int,
                  private val bus: Bus, view: View) : BaseViewHolder<Hotel>(view) {

        override fun bind(model: Hotel) {
            itemView?.setBoundEntity(model)
            itemView?.img?.loadUrl(model.imageUrl) {
                setGradient(it)
                setViewSize(it)
            }

            itemView?.title?.text = model.name

            itemView?.setOnClickListener {
                bus.post(ToHotelDetailsEvent(it.getBoundEntity<Hotel>()!!))
            }
        }

        // Add gradient based on the image's dark vibrant color
        private fun setGradient(bitmap: Bitmap) {
            var selectedColor = bitmap.getDarkestVibrantColor()
            selectedColor = (selectedColor and 0x00FFFFFF) or 0xBF000000.toInt()

            val gradient = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                    intArrayOf(selectedColor, 0x00000000))
            gradient.gradientRadius = 0f

            itemView.gradient.background = gradient
        }

        // Resize view based on Bitmap aspectRatio
        private fun setViewSize(bitmap: Bitmap) {
            val bitmapAspectRatio = bitmap.height / bitmap.width.toFloat()
            resizeViewBasedOnScreenSize(itemView, spanCount, marginDP, bitmapAspectRatio)
        }
    }
}