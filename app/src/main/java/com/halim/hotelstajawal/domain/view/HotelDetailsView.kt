package com.halim.hotelstajawal.domain.view


interface HotelDetailsView : View {

    fun showHotelImage(imageUrl: String)

    fun showHotelName(name: String)

    fun showCurrentPrice(price: Float)

    fun showPreviousPrice(price: Float)

    fun hidePreviousPriceView()

    fun showAddress(address: String)

    fun showFullHotelImage(imageUrl: String)

    fun showHotelLocation(lat: Double, lng: Double)
}