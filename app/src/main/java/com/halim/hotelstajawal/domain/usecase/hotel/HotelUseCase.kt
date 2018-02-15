package com.halim.hotelstajawal.domain.usecase.hotel

import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.usecase.UseCase


abstract class HotelUseCase<T, in Params : HotelUseCase.Params>(protected val repository: HotelRepository,
                                                                threadExecutor: ThreadExecutor,
                                                                uiExecutor: PostExecutionThread)
    : UseCase<T, Params>(threadExecutor, uiExecutor) {

    sealed class Params {

        class ListAllHotels : Params()
    }
}