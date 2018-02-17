package com.halim.hotelstajawal.data.dataset

import com.halim.hotelstajawal.Constants.TestHotel
import com.halim.hotelstajawal.data.dataset.cloud.CloudHotelDataSet
import com.halim.hotelstajawal.data.dataset.cloud.model.HotelListResponse
import com.halim.hotelstajawal.data.dataset.cloud.rest.HotelService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


class CloudHotelDataSetTest {


    @Inject
    lateinit var hotelService: HotelService
    @Inject
    lateinit var hotelDataSet: CloudHotelDataSet

    @Before
    fun init() {
        DataSetTestSuite.mapperComponent.inject(this)
    }

    @Test
    fun returnValidListReponse() {
        val hotels = TestHotel.HotelModels

        Mockito.`when`(hotelService.listHotels())
                .thenReturn(Single.just(HotelListResponse(hotels)))

        val response = hotelDataSet.listHotels()

        response.test()
                .assertNoErrors()
                .assertValue {
                    it.size == hotels.size &&
                            it[0].id == TestHotel.ID
                }
    }

    @Test
    fun returnError() {
        val e = Mockito.mock(Throwable::class.java)
        Mockito.`when`(hotelService.listHotels())
                .thenReturn(Single.error(e))

        val response = hotelDataSet.listHotels()

        response.test()
                .assertError(e)
    }
}