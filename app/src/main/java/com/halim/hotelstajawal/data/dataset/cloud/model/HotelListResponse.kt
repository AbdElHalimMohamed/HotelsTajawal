package com.halim.hotelstajawal.data.dataset.cloud.model

import com.google.gson.annotations.SerializedName
import com.halim.hotelstajawal.data.model.Hotel


data class HotelListResponse(@SerializedName("hotel") val hotels: List<Hotel>?)