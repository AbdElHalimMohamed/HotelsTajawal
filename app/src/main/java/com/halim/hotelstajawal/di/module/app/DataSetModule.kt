package com.halim.hotelstajawal.di.module.app

import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.dataset.cloud.CloudHotelDataSet
import com.halim.hotelstajawal.data.dataset.cloud.rest.HotelService
import com.halim.hotelstajawal.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [(NetworkModule::class)])
class DataSetModule {

    @Provides
    @AppScope
    fun provideHotelService(retrofit: Retrofit): HotelService =
            retrofit.create(HotelService::class.java)

    @Provides
    @AppScope
    fun provideHotelDataSet(hotelService: HotelService): HotelDataSet =
            CloudHotelDataSet(hotelService)
}