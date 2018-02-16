package com.halim.hotelstajawal.data.dataset.cloud

import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.dataset.cloud.rest.HotelService
import com.halim.hotelstajawal.data.model.Hotel
import io.reactivex.Single


class CloudHotelDataSet(private val hotelService: HotelService) : HotelDataSet {

    override fun listHotels(): Single<List<Hotel>>
            = hotelService.listHotels().map { it.hotels ?: arrayListOf()}
}