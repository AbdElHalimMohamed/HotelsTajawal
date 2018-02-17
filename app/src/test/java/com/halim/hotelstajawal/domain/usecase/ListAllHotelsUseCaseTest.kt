package com.halim.hotelstajawal.domain.usecase

import com.halim.hotelstajawal.Constants
import com.halim.hotelstajawal.di.domain.rules.RxImmediateSchedulerRule
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.usecase.hotel.HotelUseCase
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Assert
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


class ListAllHotelsUseCaseTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Inject
    lateinit var repo: HotelRepository

    @Inject
    lateinit var useCase: ListAllHotelsUseCase

    @Before
    fun init() {
        HotelUseCasesTestSuite.mapperComponent.inject(this)
    }

    @Test
    fun testListHotels() {

        Mockito.`when`(repo.listHotels())
                .thenReturn(Flowable.just(Constants.TestHotel.HotelsEntity))

        useCase.execute(HotelUseCase.Params.ListAllHotels(),
                object : DisposableSubscriber<List<Hotel>>() {

                    override fun onComplete() {}

                    override fun onNext(t: List<Hotel>?) {
                        Assert.assertNotNull(t)
                        Assert.assertTrue(t?.isNotEmpty()!!)
                    }

                    override fun onError(t: Throwable?) {
                        Assert.fail()
                    }
                })
    }
}