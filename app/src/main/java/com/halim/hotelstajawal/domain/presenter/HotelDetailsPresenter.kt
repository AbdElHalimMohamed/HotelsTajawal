package com.halim.hotelstajawal.domain.presenter

import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.presenter.events.action.ShowHotelFullImageEvent
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.HotelDetailsView


class HotelDetailsPresenter(private val hotel: Hotel, bus: Bus, view: HotelDetailsView)
    : Presenter<HotelDetailsView>(bus, view) {

    override fun registerEvents(bus: Bus) {
        registerReceivingEvent(ShowHotelFullImageEvent::class.java) {
            view.value?.showFullHotelImage(hotel.imageUrl)
        }
    }

    override fun init() {
        showHotelDetails()
    }

    private fun showHotelDetails() {
        view.value?.showHotelImage(hotel.imageUrl)
        view.value?.showHotelName(hotel.name)
        view.value?.showAddress(hotel.address.address)
        view.value?.showCurrentPrice(hotel.currentPrice)
        view.value?.showHotelLocation(hotel.address.lat, hotel.address.lng)
        if (hotel.currentPrice == hotel.prevPrice) {
            view.value?.hidePreviousPriceView()
        } else {
            view.value?.showPreviousPrice(hotel.prevPrice)
        }
    }
}