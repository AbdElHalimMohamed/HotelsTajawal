package com.halim.hotelstajawal.data.dataset.cloud.rest

import com.halim.hotelstajawal.data.dataset.cloud.model.HotelListResponse
import io.reactivex.Single
import retrofit2.http.GET


interface HotelService {

    @GET("/tajawal/hotels_exercise.json")
    fun listHotels(): Single<HotelListResponse>
}