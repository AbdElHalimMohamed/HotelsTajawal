package com.halim.hotelstajawal.data.mapper

import com.halim.hotelstajawal.Constants.TestHotel
import com.halim.hotelstajawal.data.Constant.Defaults
import com.halim.hotelstajawal.data.model.Location
import com.halim.hotelstajawal.di.data.mapper.MapperComponent
import com.halim.hotelstajawal.domain.entity.Address
import org.junit.Assert


class AddressMapperTest : BaseMapperTest<Location, Address>() {

    override fun getValidFrom(): Location? =
            Location(TestHotel.AddressStr, TestHotel.Lat, TestHotel.Lng)

    override fun getInvalidFrom(): Location? = null

    override fun getValidTo(): Address? =
            Address(TestHotel.Lat, TestHotel.Lng, TestHotel.AddressStr)

    override fun getInvalidTo(): Address? =
            Address(Defaults.Double, Defaults.Double, Defaults.String)

    override fun assertEquals(expected: Address?, actual: Address?) {
        when (expected) {
            null -> Assert.assertNull(actual)
            else -> {
                Assert.assertEquals(expected.lat, actual?.lat)
                Assert.assertEquals(expected.lng, actual?.lng)
                Assert.assertEquals(expected.address, actual?.address)
            }
        }
    }

    override fun inject(component: MapperComponent) {
        component.inject(this)
    }
}