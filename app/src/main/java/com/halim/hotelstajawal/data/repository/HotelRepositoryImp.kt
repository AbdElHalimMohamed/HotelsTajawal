package com.halim.hotelstajawal.data.repository

import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.HotelRepository
import io.reactivex.Observable


class HotelRepositoryImp(private val hotelDataSet: HotelDataSet,
                         private val hotelMapper: HotelMapper) : HotelRepository {

    override fun listHotels(): Observable<List<Hotel>>
            = hotelDataSet.listHotels().map { hotelMapper.transform(it) ?: arrayListOf() }
}