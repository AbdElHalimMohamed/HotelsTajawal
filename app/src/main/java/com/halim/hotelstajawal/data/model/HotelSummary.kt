package com.halim.hotelstajawal.data.model

import com.google.gson.annotations.SerializedName


data class HotelSummary(@SerializedName("hotelName") val hotelName: String?,
                        @SerializedName("lowRate") val lowPrice: Float?,
                        @SerializedName("highRate") val highPrice: Float?)