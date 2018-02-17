package com.halim.hotelstajawal.data.repository

import com.halim.hotelstajawal.data.repository.cache.database.HotelsRepositoryDBCacheTest
import com.halim.hotelstajawal.di.data.repository.DaggerRepositoryComponent
import com.halim.hotelstajawal.di.data.repository.RepositoryComponent
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(HotelsRepositoryDBCacheTest::class,
        HotelRepositoryTest::class)
class RepositoryTestSuite {

    companion object {
        val mapperComponent: RepositoryComponent
            get() = DaggerRepositoryComponent.builder().build()
    }
}