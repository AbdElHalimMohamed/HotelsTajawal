package com.halim.hotelstajawal

import com.halim.hotelstajawal.data.model.HotelSummary
import com.halim.hotelstajawal.data.model.Image
import com.halim.hotelstajawal.data.model.Location
import com.halim.hotelstajawal.domain.entity.Address

import com.halim.hotelstajawal.data.model.Hotel as HotelModel
import com.halim.hotelstajawal.domain.entity.Hotel as HotelEntity

object Constants {

    object TestHotel {
        const val ID = 1L
        const val ImageUrl = "JPEG"
        const val AddressStr = "Address"
        const val Lat = 40.2
        const val Lng = 14.0
        const val Name = "Name"
        const val LowPrice = 14.2F
        const val HighPrice = 45.5F
        val HotelEntity = HotelEntity(ID, Name, ImageUrl,
                Address(Lat, Lng, AddressStr),
                LowPrice, HighPrice)
        val HotelsEntity = arrayListOf(HotelEntity, HotelEntity)
        val HotelModel = HotelModel(TestHotel.ID, arrayListOf(Image(ImageUrl)),
                Location(AddressStr, Lat, Lng),
                HotelSummary(Name, LowPrice, HighPrice))
        val HotelModels = arrayListOf(HotelModel, HotelModel)
    }
}