package com.halim.hotelstajawal.di.domain.usecase

import com.halim.hotelstajawal.di.scope.PerTestClass
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.excutor.ThreadPoolExecutor
import com.halim.hotelstajawal.domain.excutor.UIExecutor
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
class HotelUseCasesModule {

    @Provides
    @PerTestClass
    fun provideHotelRepository(): HotelRepository = mock(HotelRepository::class.java)

    @Provides
    @PerTestClass
    fun provideThreadExecutor(): ThreadExecutor = ThreadPoolExecutor()

    @Provides
    @PerTestClass
    fun providePostExecutionThread(): PostExecutionThread = UIExecutor()

    @Provides
    @PerTestClass
    fun provideListAllHotelsUseCase(repository: HotelRepository, threadExecutor: ThreadExecutor,
                                    uiExecutor: PostExecutionThread) =
            ListAllHotelsUseCase(repository, threadExecutor, uiExecutor)
}