package com.halim.hotelstajawal.di.data.dataset

import com.halim.hotelstajawal.data.dataset.CloudHotelDataSetTest
import com.halim.hotelstajawal.di.scope.PerTestClass
import dagger.Component

@Component(modules = [(DataSetModule::class)])
@PerTestClass
interface DataSetComponent {

    fun inject(test: CloudHotelDataSetTest)
}