package com.halim.hotelstajawal.domain.entity


class Hotel(id: Long,
            val name: String,
            val imageUrl: String,
            val address: Address,
            val currentPrice: Float,
            val prevPrice: Float) : Entity(id)