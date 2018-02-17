package com.halim.hotelstajawal.domain.presenter

import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.presenter.events.navigation.ToHotelDetailsEvent
import com.halim.hotelstajawal.domain.usecase.hotel.HotelUseCase
import com.halim.hotelstajawal.domain.usecase.hotel.ListAllHotelsUseCase
import com.halim.hotelstajawal.domain.usecase.observers.RetryDisposableSubscriber
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.HotelListView


class HotelListPresenter(private val listUseCase: ListAllHotelsUseCase,
                         private val threadExecutor: ThreadExecutor,
                         private val uiExecutor: PostExecutionThread,
                         bus: Bus, view: HotelListView) : Presenter<HotelListView>(bus, view) {

    override fun registerEvents(bus: Bus) {
        registerReceivingEvent(ToHotelDetailsEvent::class.java) {
            view.value?.showHotelDetails(it.hotel)
        }
    }

    override fun init() {
        listAllHotels()
    }

    private fun listAllHotels() {

        listUseCase.execute(HotelUseCase.Params.ListAllHotels(),
                object : RetryDisposableSubscriber<List<Hotel>>(threadExecutor, uiExecutor, view) {

                    override fun onNext(data: List<Hotel>) {
                        super.onNext(data)
                        if (data.isEmpty()) {
                            view.value?.showEmptyResult()
                        } else {
                            view.value?.showHotelList(data)
                        }
                    }
                })
    }

    override fun dispose() {
        super.dispose()

        listUseCase.dispose()
    }
}