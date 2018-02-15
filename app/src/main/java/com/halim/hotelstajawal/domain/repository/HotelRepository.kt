package com.halim.hotelstajawal.domain.repository

import com.halim.hotelstajawal.domain.entity.Hotel
import io.reactivex.Observable

// Interface to be used by data layer
interface HotelRepository {

    fun listHotels(): Observable<List<Hotel>>
}