package com.halim.hotelstajawal.di.module.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.halim.hotelstajawal.di.scope.ActivityScope
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.presenter.HotelListPresenter
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import com.halim.hotelstajawal.domain.view.HotelListView
import com.halim.hotelstajawal.ui.activity.HotelListActivity
import com.halim.hotelstajawal.ui.viewmodel.HotelListViewModel
import com.halim.hotelstajawal.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [(HotelListModule.Bind::class)])
class HotelListModule {

    /* We are using Lazy to provide dependencies because Dagger will create new dependencies
       for HotelListViewModel despite that ViewModelProviders will use the old instance of
       HotelListViewModel, if no instance available, new one will be lazy initialized.
    */
    @Provides
    @ActivityScope
    fun provideListAllHotelsUseCase(repository: HotelRepository, threadExecutor: ThreadExecutor,
                                    uiExecutor: PostExecutionThread): Lazy<ListAllHotelsUseCase> =
            lazy { ListAllHotelsUseCase(repository, threadExecutor, uiExecutor) }

    @Provides
    @ActivityScope
    fun provideHotelListViewModel(listUseCase: Lazy<ListAllHotelsUseCase>, view: HotelListView): Lazy<HotelListViewModel> =
            lazy { HotelListViewModel(HotelListPresenter(listUseCase.value, view)) }

    @Provides
    @ActivityScope
    fun provideViewModelFactory(viewModel: Lazy<HotelListViewModel>): ViewModelProvider.Factory =
            ViewModelFactory(lazy { viewModel.value })

    // Get the same HotelListViewModel for the activity during configuration change
    @Provides
    @ActivityScope
    fun provideHotelListPresenter(owner: FragmentActivity, viewModelFactory: ViewModelProvider.Factory): HotelListPresenter =
            ViewModelProviders.of(owner, viewModelFactory).get(HotelListViewModel::class.java).presenter

    @Module
    abstract class Bind {
        @Binds
        @ActivityScope
        abstract fun bindView(activity: HotelListActivity): HotelListView

        @Binds
        @ActivityScope
        abstract fun bindViewModelOwner(activity: HotelListActivity): FragmentActivity
    }
}