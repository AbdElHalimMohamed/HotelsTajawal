package com.halim.hotelstajawal.data.repository

import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.repository.cache.HotelsRepositoryCache
import io.reactivex.Flowable
import io.reactivex.Observable


class HotelRepositoryImp(private val hotelDataSet: HotelDataSet,
                         private val hotelMapper: HotelMapper,
                         private val cacheRepo: HotelsRepositoryCache) : HotelRepository {

    override fun listHotels(): Flowable<List<Hotel>> =
            when {
                cacheRepo.isExpired().not() -> cacheRepo.listHotels()
                else -> hotelDataSet.listHotels().map {
                    val hotels = hotelMapper.transform(it) ?: arrayListOf()
                    cacheRepo.saveHotels(hotels)
                    hotels
                }.concatWith { cacheRepo.listHotels()  }
            }
}