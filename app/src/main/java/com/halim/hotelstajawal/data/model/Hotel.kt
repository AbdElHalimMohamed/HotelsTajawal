package com.halim.hotelstajawal.data.model

import com.google.gson.annotations.SerializedName


data class Hotel(@SerializedName("hotelId") val id: Long?,
                 @SerializedName("image") val images: List<Image>?,
                 @SerializedName("location") val location: Location?,
                 @SerializedName("summary") val summary: HotelSummary?)