package com.halim.hotelstajawal.di.module.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.halim.hotelstajawal.di.scope.ActivityScope
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.presenter.HotelDetailsPresenter
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.view.HotelDetailsView
import com.halim.hotelstajawal.ui.activity.HotelDetailsActivity
import com.halim.hotelstajawal.ui.viewmodel.HotelDetailsViewModel
import com.halim.hotelstajawal.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [(HotelDetailModule.Bind::class)])
class HotelDetailModule {

    @Provides
    @ActivityScope
    fun provideHotel(activity: HotelDetailsActivity): Lazy<Hotel> =
            lazy { activity.intent.getParcelableExtra(HotelDetailsActivity.HotelIntent) as Hotel }

    @Provides
    @ActivityScope
    fun provideHotelDetailsViewModel(hotel: Lazy<Hotel>, bus: Bus, view: HotelDetailsView): Lazy<HotelDetailsViewModel> =
            lazy { HotelDetailsViewModel(HotelDetailsPresenter(hotel.value, bus, view)) }

    @Provides
    @ActivityScope
    fun provideViewModelFactory(viewModel: Lazy<HotelDetailsViewModel>): ViewModelProvider.Factory =
            ViewModelFactory(lazy { viewModel.value })

    @Provides
    @ActivityScope
    fun provideHotelDetailsPresenter(owner: FragmentActivity, viewModelFactory: ViewModelProvider.Factory,
                                     view: HotelDetailsView): HotelDetailsPresenter =
            ViewModelProviders.of(owner, viewModelFactory).get(HotelDetailsViewModel::class.java).presenter.also {
                it.updateView(view)
            }


    @Module
    abstract class Bind {
        @Binds
        @ActivityScope
        abstract fun bindView(activity: HotelDetailsActivity): HotelDetailsView

        @Binds
        @ActivityScope
        abstract fun bindViewModelOwner(activity: HotelDetailsActivity): FragmentActivity
    }
}