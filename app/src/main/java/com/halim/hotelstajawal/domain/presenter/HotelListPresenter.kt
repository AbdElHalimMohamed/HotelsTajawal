package com.halim.hotelstajawal.domain.presenter

import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.usecase.hotel.HotelUseCase
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import com.halim.hotelstajawal.domain.usecase.observers.RetryDisposableObserver
import com.halim.hotelstajawal.domain.usecase.observers.SimpleDisposableObserver
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.HotelListView


class HotelListPresenter(private val listUseCase: ListAllHotelsUseCase,
                         view: HotelListView) : Presenter<HotelListView>(view) {

    fun listAllHotels() {

        listUseCase.execute(HotelUseCase.Params.ListAllHotels(),
                object : RetryDisposableObserver<List<Hotel>>(view) {

                    override fun onNext(data: List<Hotel>) {
                        super.onNext(data)
                        view.value?.showHotelList(data)
                    }
                })
    }

    override fun dispose() {
        super.dispose()

        listUseCase.dispose()
    }
}