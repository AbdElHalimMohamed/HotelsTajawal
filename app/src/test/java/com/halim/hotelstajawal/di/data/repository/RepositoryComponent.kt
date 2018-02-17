package com.halim.hotelstajawal.di.data.repository

import com.halim.hotelstajawal.data.repository.HotelRepositoryTest
import com.halim.hotelstajawal.data.repository.cache.database.HotelsRepositoryDBCacheTest
import com.halim.hotelstajawal.di.scope.PerTestClass
import dagger.Component


@Component(modules = [(RepositoryModule::class)])
@PerTestClass
interface RepositoryComponent {

    fun inject(test: HotelsRepositoryDBCacheTest)

    fun inject(test: HotelRepositoryTest)
}