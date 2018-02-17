package com.halim.hotelstajawal.domain.usecase

import com.halim.hotelstajawal.di.domain.usecase.DaggerHotelUseCasesComponent
import com.halim.hotelstajawal.di.domain.usecase.HotelUseCasesComponent
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(ListAllHotelsUseCaseTest::class)
class HotelUseCasesTestSuite {

    companion object {
        val mapperComponent: HotelUseCasesComponent
            get() = DaggerHotelUseCasesComponent.builder().build()
    }
}