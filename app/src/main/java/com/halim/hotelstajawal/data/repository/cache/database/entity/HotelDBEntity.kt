package com.halim.hotelstajawal.data.repository.cache.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "hotel")
data class HotelDBEntity(@PrimaryKey @ColumnInfo(name = "_id") val id: Long,
                         @ColumnInfo(name = "name") val name: String,
                         @ColumnInfo(name = "image_url") val image: String,
                         @ColumnInfo(name = "address") val address: String,
                         @ColumnInfo(name = "lat") val lat: Double,
                         @ColumnInfo(name = "lng") val lng: Double,
                         @ColumnInfo(name = "low_price") val lowPrice: Float,
                         @ColumnInfo(name = "high_price") val highPrice: Float)