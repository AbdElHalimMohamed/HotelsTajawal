package com.halim.hotelstajawal.di.module.app

import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.mapper.AddressMapper
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.data.repository.HotelRepositoryImp
import com.halim.hotelstajawal.di.scope.AppScope
import com.halim.hotelstajawal.domain.repository.HotelRepository
import dagger.Module
import dagger.Provides


@Module(includes = [(DataSetModule::class)])
class RepositoryModule {

    //======================= Hotel Repository =======================
    @Provides
    @AppScope
    fun provideAddressMapper() = AddressMapper()

    @Provides
    @AppScope
    fun provideHotelMapper(addressMapper: AddressMapper) = HotelMapper(addressMapper)

    @Provides
    @AppScope
    fun provideHotelRepository(hotelDataSet: HotelDataSet,
                               hotelMapper: HotelMapper): HotelRepository =
            HotelRepositoryImp(hotelDataSet, hotelMapper)
}