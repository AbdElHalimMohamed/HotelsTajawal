package com.halim.hotelstajawal.di.domain.usecase

import com.halim.hotelstajawal.di.scope.PerTestClass
import com.halim.hotelstajawal.domain.usecase.ListAllHotelsUseCaseTest
import dagger.Component


@Component(modules = [(HotelUseCasesModule::class)])
@PerTestClass
interface HotelUseCasesComponent {

    fun inject(test: ListAllHotelsUseCaseTest)
}