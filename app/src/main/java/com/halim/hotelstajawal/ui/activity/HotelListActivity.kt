package com.halim.hotelstajawal.ui.activity

import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.domain.entity.Hotel
import com.halim.hotelstajawal.domain.presenter.HotelListPresenter
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.view.HotelListView
import com.halim.hotelstajawal.ui.adapter.HotelsAdapter
import com.halim.hotelstajawal.ui.adapter.decorator.SpacesItemDecoration
import com.halim.hotelstajawal.ui.startActivity
import kotlinx.android.synthetic.main.activity_hotel_list.*
import javax.inject.Inject


class HotelListActivity : BaseActivity<HotelListPresenter>(), HotelListView {

    override fun getLayoutId(): Int = R.layout.activity_hotel_list

    override fun onViewCreated(presenter: HotelListPresenter) {
        val spanCount = 2
        val spaceDP = 4

        hotelsRV.adapter = HotelsAdapter(bus, spanCount, spaceDP)
        hotelsRV.layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        hotelsRV.addItemDecoration(SpacesItemDecoration(spaceDP, this))
    }

    override fun getActivityTitle(): String? = getString(R.string.hotel_list_title)

    override fun getEmptyViewMsgRes(): Int = R.string.empty_hotel_list

    override fun getToolbar(): Toolbar? = listToolbar

    override fun showHotelDetails(hotel: Hotel) {
        startActivity(HotelDetailsActivity::class, HotelDetailsActivity.HotelIntent,
                hotel)
    }

    override fun showHotelList(hotels: List<Hotel>) {
        (hotelsRV.adapter as HotelsAdapter).setItems(hotels)
    }
}