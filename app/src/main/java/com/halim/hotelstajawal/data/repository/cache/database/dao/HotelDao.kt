package com.halim.hotelstajawal.data.repository.cache.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.halim.hotelstajawal.data.repository.cache.database.entity.HotelDBEntity
import io.reactivex.Flowable

@Dao
interface HotelDao {

    @Query("SELECT * from hotel")
    fun getAllHotels(): Flowable<List<HotelDBEntity>>

    @Insert(onConflict = REPLACE)
    fun insertHotels(hotels: List<HotelDBEntity>)
}