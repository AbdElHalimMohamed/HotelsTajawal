package com.halim.hotelstajawal.data.mapper


import com.halim.hotelstajawal.Constants.TestHotel
import com.halim.hotelstajawal.di.data.mapper.MapperComponent
import org.junit.Assert
import com.halim.hotelstajawal.data.model.Hotel as HotelModel
import com.halim.hotelstajawal.domain.entity.Hotel as HotelEntity

class HotelMapperTest : BaseMapperTest<HotelModel, HotelEntity>() {

    override fun getValidFrom(): HotelModel? = TestHotel.HotelModel

    override fun getInvalidFrom(): HotelModel? = null

    override fun getValidTo(): HotelEntity? = TestHotel.HotelEntity

    override fun getInvalidTo(): HotelEntity? = null

    override fun inject(component: MapperComponent) {
        component.inject(this)
    }

    override fun assertEquals(expected: HotelEntity?, actual: HotelEntity?) {
        when (expected) {
            null -> Assert.assertNull(actual)
            else -> {
                Assert.assertEquals(expected.id, actual?.id)
                Assert.assertEquals(expected.name, actual?.name)
                Assert.assertEquals(expected.imageUrl, actual?.imageUrl)
                Assert.assertEquals(expected.address.address, actual?.address?.address)
                Assert.assertEquals(expected.address.lat, actual?.address?.lat)
                Assert.assertEquals(expected.address.lng, actual?.address?.lng)
                Assert.assertEquals(expected.currentPrice, actual?.currentPrice)
                Assert.assertEquals(expected.prevPrice, actual?.prevPrice)
            }
        }
    }
}