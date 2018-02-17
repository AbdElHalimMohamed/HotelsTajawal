package com.halim.hotelstajawal.di.module.app

import com.halim.hotelstajawal.di.module.activity.HotelDetailModule
import com.halim.hotelstajawal.di.module.activity.HotelListModule
import com.halim.hotelstajawal.di.scope.ActivityScope
import com.halim.hotelstajawal.ui.activity.HotelDetailsActivity
import com.halim.hotelstajawal.ui.activity.HotelListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(HotelListModule::class)])
    abstract fun createHotelListActivityInjector(): HotelListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(HotelDetailModule::class)])
    abstract fun createHotelDetailsActivityInjector(): HotelDetailsActivity
}