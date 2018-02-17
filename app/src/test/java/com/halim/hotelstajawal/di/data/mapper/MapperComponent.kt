package com.halim.hotelstajawal.di.data.mapper

import com.halim.hotelstajawal.data.mapper.AddressMapperTest
import com.halim.hotelstajawal.data.mapper.HotelMapperTest
import dagger.Component


@Component(modules = [(MapperModule::class)])
interface MapperComponent {

    fun inject(test: HotelMapperTest)

    fun inject(test: AddressMapperTest)
}