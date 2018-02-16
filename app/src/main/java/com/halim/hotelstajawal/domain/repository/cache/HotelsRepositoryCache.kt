package com.halim.hotelstajawal.domain.repository.cache

import com.halim.hotelstajawal.domain.entity.Hotel
import io.reactivex.Flowable


interface HotelsRepositoryCache : RepositoryCache {

    fun listHotels(): Flowable<List<Hotel>>

    fun saveHotels(hotels: List<Hotel>)
}