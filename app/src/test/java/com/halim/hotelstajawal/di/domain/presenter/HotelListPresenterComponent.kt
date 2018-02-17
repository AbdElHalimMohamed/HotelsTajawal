package com.halim.hotelstajawal.di.domain.presenter

import com.halim.hotelstajawal.di.scope.PerTestClass
import dagger.Component


@Component(modules = [(HotelListPresenterModule::class)])
@PerTestClass
interface HotelListPresenterComponent {

}