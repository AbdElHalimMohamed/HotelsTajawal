package com.halim.hotelstajawal.domain.usecase.hotel

import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.repository.HotelRepository
import io.reactivex.Flowable


class ListAllHotelsUseCase(repository: HotelRepository, threadExecutor: ThreadExecutor,
                           uiExecutor: PostExecutionThread)
    : HotelUseCase<List<Hotel>, HotelUseCase.Params.ListAllHotels>(repository, threadExecutor, uiExecutor) {

    // based on use case, data can be modified using RxJava operators before handed to Presenter
    override fun buildUseCaseObservable(params: Params.ListAllHotels): Flowable<List<Hotel>> =
            repository.listHotels()
}