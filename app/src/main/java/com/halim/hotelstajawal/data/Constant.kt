package com.halim.hotelstajawal.data

import com.halim.hotelstajawal.domain.entity.Address


object Constant {

    object Defaults {
        val ID = -1L
        val Image = ".jpg"
        val String = "" // OR "<No content>"
        val Double = 0.0
        val Float = 0F
        val AddressEntity = Address(Double, Double, String)
    }

    object Network {

        val NetWorkCacheSize = 10L * 1024 * 1024
    }
}