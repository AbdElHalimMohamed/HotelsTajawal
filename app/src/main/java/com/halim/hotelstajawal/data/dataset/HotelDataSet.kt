package com.halim.hotelstajawal.data.dataset

import com.halim.hotelstajawal.data.model.Hotel
import io.reactivex.Single

// a common interface for all source of data (CloudDataSet, DatabaseDataSet, FileDataSet...etc)
interface HotelDataSet {

    fun listHotels(): Single<List<Hotel>>
}