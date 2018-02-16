package com.halim.hotelstajawal.data.repository.cache

import android.content.Context
import android.content.SharedPreferences
import com.halim.hotelstajawal.di.qualifier.AppQualifier
import com.halim.hotelstajawal.domain.repository.cache.CachePreferencesHelper
import javax.inject.Inject


class CachePreferencesHelperImp @Inject constructor(@AppQualifier context: Context) : CachePreferencesHelper {

    companion object {
        val CachePreferencesName = "com.halim.hotelstajawal.data.cache"
    }

    private val pref: SharedPreferences = context.getSharedPreferences(CachePreferencesName, Context.MODE_PRIVATE)

    override fun setLastCacheTime(key: String, time: Long) {
        pref.edit().putLong(key, time).apply()
    }

    override fun getLastCacheTime(key: String): Long =
            pref.getLong(key, 0L)


}