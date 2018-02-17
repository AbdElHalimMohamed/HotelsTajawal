package com.halim.hotelstajawal.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.ui.loadUrl
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase
import kotlinx.android.synthetic.main.activity_full_screen_image.*


class FullScreenImageActivity : AppCompatActivity() {

    companion object {
        const val ImageUrl = "img_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val imageUrl = intent.getStringExtra(ImageUrl)

        if (imageUrl != null) {
            image.displayType = ImageViewTouchBase.DisplayType.FIT_TO_SCREEN
            image.loadUrl(imageUrl)
            image.setSingleTapListener {
                supportFinishAfterTransition()
            }
        }
    }
}