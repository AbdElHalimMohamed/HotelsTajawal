package com.halim.hotelstajawal.data.mapper

import com.halim.hotelstajawal.di.data.mapper.DaggerMapperComponent
import com.halim.hotelstajawal.di.data.mapper.MapperComponent
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(HotelMapperTest::class, AddressMapperTest::class)
class MapperTestSuite {

    companion object {
        val mapperComponent: MapperComponent
            get() = DaggerMapperComponent.builder().build()
    }
}