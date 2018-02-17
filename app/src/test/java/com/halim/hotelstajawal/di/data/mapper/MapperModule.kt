package com.halim.hotelstajawal.di.data.mapper

import com.halim.hotelstajawal.data.mapper.AddressMapper
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.data.mapper.Mapper
import com.halim.hotelstajawal.data.model.Location
import com.halim.hotelstajawal.domain.entity.Address
import dagger.Module
import dagger.Provides
import com.halim.hotelstajawal.data.model.Hotel as HotelModel
import com.halim.hotelstajawal.domain.entity.Hotel as HotelEntity

@Module
class MapperModule {

    @Provides
    fun provideAddressMapper(): Mapper<Location, Address> = AddressMapper()

    @Provides
    fun provideHotelMapper(addressMapper: Mapper<Location, Address>): Mapper<HotelModel, HotelEntity> =
            HotelMapper(addressMapper as AddressMapper)
}