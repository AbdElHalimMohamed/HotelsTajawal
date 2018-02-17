package com.halim.hotelstajawal.di.domain.presenter

import com.halim.hotelstajawal.di.scope.PerTestClass
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.presenter.HotelListPresenter
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import com.halim.hotelstajawal.domain.view.HotelListView
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
class HotelListPresenterModule {

    @Provides
    @PerTestClass
    fun provideHotelListView() = mock(HotelListView::class.java)

    @Provides
    @PerTestClass
    fun provideBus() = mock(Bus::class.java)

    @Provides
    @PerTestClass
    fun providePostExecutionThread() = mock(PostExecutionThread::class.java)

    @Provides
    @PerTestClass
    fun provideThreadExecutor() = mock(ThreadExecutor::class.java)

    @Provides
    @PerTestClass
    fun provideListAllHotelsUseCase() = mock(ListAllHotelsUseCase::class.java)

    @Provides
    @PerTestClass
    fun provideHotelListPresenter(listUseCase: ListAllHotelsUseCase, threadExecutor: ThreadExecutor,
                                  uiExecutor: PostExecutionThread, bus: Bus, view: HotelListView) =
            HotelListPresenter(listUseCase, threadExecutor, uiExecutor, bus, view)
}