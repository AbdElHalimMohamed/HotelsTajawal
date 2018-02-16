package com.halim.hotelstajawal.domain.view

import com.halim.hotelstajawal.domain.entity.Hotel


interface HotelListView : View {

    fun showHotelList(hotels: List<Hotel>)

    fun showHotelDetails(hotel: Hotel)
}