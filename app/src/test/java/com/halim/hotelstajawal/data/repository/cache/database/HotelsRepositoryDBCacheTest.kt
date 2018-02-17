package com.halim.hotelstajawal.data.repository.cache.database

import com.halim.hotelstajawal.Constants
import com.halim.hotelstajawal.data.repository.RepositoryTestSuite
import com.halim.hotelstajawal.domain.Constant
import com.halim.hotelstajawal.domain.entity.Address
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.cache.CachePreferencesHelper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class HotelsRepositoryDBCacheTest {

    @Inject
    lateinit var cacheRepo: HotelsRepositoryDBCache

    @Inject
    lateinit var prefHelper: CachePreferencesHelper

    @Inject
    lateinit var db: HotelsDatabase

    @Before
    fun init() {
        RepositoryTestSuite.mapperComponent.inject(this)
    }

    @After
    fun clear() {
        db.close()
    }

    @Test
    fun saveHotels() {

        populateDB()

        val list = cacheRepo.listHotels().blockingFirst()
        Assert.assertTrue(list.isNotEmpty())

        Mockito.verify(prefHelper).setLastCacheTime(anyString(), anyLong())
    }

    @Test
    fun listValues() {

        populateDB()

        val list = cacheRepo.listHotels().blockingFirst()
        Assert.assertTrue(list.isNotEmpty())
        Assert.assertEquals(Constants.TestHotel.ID, list[0].id)
    }

    @Test
    fun testExpiration() {
        val beforeExpireTime = System.currentTimeMillis() - Constant.Cache.EXPIRATION_TIME - 1000000
        val afterExpireTime = System.currentTimeMillis() + Constant.Cache.EXPIRATION_TIME + 1000000

        Mockito.`when`(prefHelper.getLastCacheTime(anyString()))
                .thenReturn(afterExpireTime)

        Assert.assertFalse(cacheRepo.isExpired())

        Mockito.`when`(prefHelper.getLastCacheTime(anyString()))
                .thenReturn(beforeExpireTime)

        Assert.assertTrue(cacheRepo.isExpired())
    }

    private fun populateDB() {
        val hotel = Hotel(Constants.TestHotel.ID, Constants.TestHotel.Name, Constants.TestHotel.ImageUrl,
                Address(Constants.TestHotel.Lat, Constants.TestHotel.Lng, Constants.TestHotel.AddressStr),
                Constants.TestHotel.LowPrice, Constants.TestHotel.HighPrice)
        val hotels = arrayListOf(hotel)

        cacheRepo.saveHotels(hotels)
    }
}