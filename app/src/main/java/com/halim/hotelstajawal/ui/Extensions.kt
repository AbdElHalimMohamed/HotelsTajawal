package com.halim.hotelstajawal.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.halim.hotelstajawal.di.module.app.GlideApp


fun Context.dp2Px(dp: Float): Float {
    val displayMetrics = resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun Bitmap.getDarkestVibrantColor(): Int {
    val palette = Palette.from(this).generate()
    val swatches = arrayListOf(palette.darkVibrantSwatch, palette.darkMutedSwatch)
    val theSwatch = swatches.firstOrNull { it != null }
    return theSwatch?.rgb ?: 0x000000
}

fun ImageView.loadUrl(url: String?, placeholder: Int = -1, error: Int = -1, onReady: (Bitmap) -> Unit = {}) {

    val glideRequest = GlideApp.with(context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean = false

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (resource != null) {
                        when(resource) {
                            is BitmapDrawable -> onReady(resource.bitmap)
                            is GifDrawable -> onReady(resource.firstFrame)
                        }
                    }
                    return false
                }

            })

    if (placeholder != -1)
        glideRequest.placeholder(placeholder)

    if (error != -1)
        glideRequest.error(error)

    glideRequest.into(this)
}