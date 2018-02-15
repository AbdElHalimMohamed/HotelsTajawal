package com.halim.hotelstajawal.ui.activity

import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.presenter.HotelListPresenter
import com.halim.hotelstajawal.domain.view.HotelListView


class HotelListActivity : BaseActivity<HotelListPresenter>(), HotelListView {

    override fun getLayoutId(): Int = R.layout.activity_hotel_list

    override fun onViewCreated(presenter: HotelListPresenter) {
        presenter.listAllHotels()
    }

    override fun showHotelList(hotels: List<Hotel>) {

    }
}