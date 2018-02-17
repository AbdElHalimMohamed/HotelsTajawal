package com.halim.hotelstajawal.data.dataset

import com.halim.hotelstajawal.di.data.dataset.DaggerDataSetComponent
import com.halim.hotelstajawal.di.data.dataset.DataSetComponent
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(CloudHotelDataSetTest::class)
class DataSetTestSuite {

    companion object {
        val mapperComponent: DataSetComponent
            get() = DaggerDataSetComponent.builder().build()
    }
}