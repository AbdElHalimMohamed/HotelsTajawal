package com.halim.hotelstajawal.data.repository

import com.halim.hotelstajawal.Constants
import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.cache.HotelsRepositoryCache
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito
import javax.inject.Inject


class HotelRepositoryTest {

    @Inject
    lateinit var hotelDataSet: HotelDataSet

    @Inject
    lateinit var cacheRepo: HotelsRepositoryCache

    @Inject
    lateinit var repository: HotelRepositoryImp

    @Before
    fun init() {
        RepositoryTestSuite.mapperComponent.inject(this)
    }

    @Test
    fun listHotelsFromCache() {

        Mockito.`when`(cacheRepo.isExpired())
                .thenReturn(false)
        Mockito.`when`(cacheRepo.listHotels())
                .thenReturn(Flowable.just(Constants.TestHotel.HotelsEntity))

        repository.listHotels().test()
                .assertNoErrors()
                .assertValue {
                    it.isNotEmpty()
                }
    }

    @Test
    fun listHotelsFromDataSet() {
        Mockito.`when`(cacheRepo.isExpired())
                .thenReturn(true)
        Mockito.`when`(hotelDataSet.listHotels())
                .thenReturn(Single.just(Constants.TestHotel.HotelModels))

        repository.listHotels().test()
                .assertNoErrors()
                .assertValue {
                    it.isNotEmpty()
                }

        Mockito.verify(cacheRepo).saveHotels(anyList<Hotel>())
    }
}