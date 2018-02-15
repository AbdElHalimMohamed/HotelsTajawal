package com.halim.hotelstajawal.di.module.activity

import com.halim.hotelstajawal.di.scope.ActivityScope
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.presenter.HotelListPresenter
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import com.halim.hotelstajawal.domain.view.HotelListView
import com.halim.hotelstajawal.ui.activity.HotelListActivity
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [(HotelListModule.Bind::class)])
class HotelListModule {

    @Provides
    @ActivityScope
    fun provideListAllHotelsUseCase(repository: HotelRepository, threadExecutor: ThreadExecutor,
                                    uiExecutor: PostExecutionThread) =
            ListAllHotelsUseCase(repository, threadExecutor, uiExecutor)

    @Provides
    @ActivityScope
    fun provideHotelListPresenter(listUseCase: ListAllHotelsUseCase, view: HotelListView) =
            HotelListPresenter(listUseCase, view)

    @Module
    abstract class Bind {
        @Binds
        @ActivityScope
        abstract fun bindView(activity: HotelListActivity): HotelListView
    }
}