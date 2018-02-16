package com.halim.hotelstajawal.data.repository.cache.database

import com.halim.hotelstajawal.data.repository.cache.database.dao.HotelDao
import com.halim.hotelstajawal.data.repository.cache.database.entity.HotelDBEntity
import com.halim.hotelstajawal.domain.Constant
import com.halim.hotelstajawal.domain.entity.Address
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.cache.CachePreferencesHelper
import com.halim.hotelstajawal.domain.repository.cache.HotelsRepositoryCache
import io.reactivex.Flowable


class HotelsRepositoryDBCache(private val hotelDao: HotelDao,
                              private val prefHelper: CachePreferencesHelper) : HotelsRepositoryCache {

    companion object {
        val CacheKey = "Hotels"
    }

    override fun listHotels(): Flowable<List<Hotel>> =
            hotelDao.getAllHotels().map {
                it.map { entity ->
                    Hotel(entity.id, entity.name, entity.image, Address(entity.lat, entity.lng, entity.address),
                            entity.lowPrice, entity.highPrice)
                }
            }

    override fun saveHotels(hotels: List<Hotel>) {
        hotelDao.insertHotels(hotels.map { hotel ->
            HotelDBEntity(hotel.id, hotel.name, hotel.imageUrl, hotel.address.address,
                    hotel.address.lat, hotel.address.lng, hotel.currentPrice, hotel.prevPrice)
        })
        prefHelper.setLastCacheTime(CacheKey, System.currentTimeMillis())
    }

    override fun isExpired(): Boolean =
            System.currentTimeMillis() - prefHelper.getLastCacheTime(CacheKey) > Constant.Cache.EXPIRATION_TIME
}