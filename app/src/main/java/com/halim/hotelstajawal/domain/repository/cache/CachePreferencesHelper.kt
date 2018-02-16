package com.halim.hotelstajawal.domain.repository.cache


interface CachePreferencesHelper {

    fun setLastCacheTime(key: String, time: Long)

    fun getLastCacheTime(key: String): Long
}