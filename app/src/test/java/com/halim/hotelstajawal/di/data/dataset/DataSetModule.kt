package com.halim.hotelstajawal.di.data.dataset

import com.halim.hotelstajawal.data.dataset.cloud.CloudHotelDataSet
import com.halim.hotelstajawal.data.dataset.cloud.rest.HotelService
import com.halim.hotelstajawal.di.scope.PerTestClass
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
class DataSetModule {

    @Provides
    @PerTestClass
    fun provideHotelService(): HotelService = mock(HotelService::class.java)

    @Provides
    @PerTestClass
    fun provideHotelCloudDataSet(service: HotelService) = CloudHotelDataSet(service)
}