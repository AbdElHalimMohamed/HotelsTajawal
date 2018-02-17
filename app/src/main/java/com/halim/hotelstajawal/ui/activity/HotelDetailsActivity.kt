package com.halim.hotelstajawal.ui.activity

import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.Toolbar
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.domain.presenter.HotelDetailsPresenter
import com.halim.hotelstajawal.domain.presenter.events.action.ShowHotelFullImageEvent
import com.halim.hotelstajawal.domain.view.HotelDetailsView
import com.halim.hotelstajawal.ui.loadUrl
import com.halim.hotelstajawal.ui.startActivity
import kotlinx.android.synthetic.main.activity_hotel_details.*


class HotelDetailsActivity : BaseActivity<HotelDetailsPresenter>(),
        HotelDetailsView, OnMapReadyCallback {

    companion object {
        const val HotelIntent = "hotel"
    }

    private lateinit var latLng: LatLng

    override fun getLayoutId(): Int = R.layout.activity_hotel_details

    override fun onViewCreated(presenter: HotelDetailsPresenter) {
        // add strike through
        prevPriceTV.paintFlags = prevPriceTV.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
        currentPriceTV.typeface = Typeface.DEFAULT_BOLD
        hotelImage.setOnClickListener {
            bus.post(ShowHotelFullImageEvent())
        }
    }

    private fun initMap() {
        with(mapView) {
            onCreate(null)
            getMapAsync(this@HotelDetailsActivity)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        MapsInitializer.initialize(this)

        if (map != null && this::latLng.isInitialized) {
            with(map) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
                addMarker(MarkerOptions().position(latLng))
                mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }
    }

    override fun showHotelLocation(lat: Double, lng: Double) {
        latLng = LatLng(lat, lng)

        initMap()
    }

    override fun showHotelImage(imageUrl: String) {
        hotelImage.loadUrl(imageUrl)
    }

    override fun showHotelName(name: String) {
        title = name
        hotelNameTV.text = name
    }

    override fun showCurrentPrice(price: Float) {
        currentPriceTV.text = price.toString()
    }

    override fun showPreviousPrice(price: Float) {
        prevPriceTV.text = price.toString()
    }

    override fun hidePreviousPriceView() {
        prevPriceTV.visibility = View.INVISIBLE
    }

    override fun showAddress(address: String) {
        addressTV.text = address
    }

    override fun showFullHotelImage(imageUrl: String) {
        startActivity(FullScreenImageActivity::class, FullScreenImageActivity.ImageUrl,
                imageUrl)
    }

    override fun getToolbar(): Toolbar? = detailsToolbar

    override fun showHomeAsUp(): Boolean = true
}