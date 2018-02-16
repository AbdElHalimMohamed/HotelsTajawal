package com.halim.hotelstajawal.data.repository.cache.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.halim.hotelstajawal.data.repository.cache.database.dao.HotelDao
import com.halim.hotelstajawal.data.repository.cache.database.entity.HotelDBEntity

@Database(entities = [(HotelDBEntity::class)], version = 1)
abstract class HotelsDatabase : RoomDatabase() {

    abstract fun getHotelDao(): HotelDao

    companion object Instance {
        val NAME = "hotels.db"
        private var INSTANCE: HotelsDatabase? = null

        fun getInstance(context: Context): HotelsDatabase {

            if (INSTANCE == null) {
                synchronized(HotelsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            HotelsDatabase::class.java, NAME)
                            .build()
                }
            }

            return INSTANCE!!
        }
    }
}