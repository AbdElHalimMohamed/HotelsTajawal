package com.halim.hotelstajawal.data.model

import com.google.gson.annotations.SerializedName


data class Location(@SerializedName("address") val address: String?,
                    @SerializedName("latitude") val lat: Double?,
                    @SerializedName("longitude") val lng: Double?)