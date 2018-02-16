package com.halim.hotelstajawal.di.module.app

import android.content.Context
import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.mapper.AddressMapper
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.data.repository.HotelRepositoryImp
import com.halim.hotelstajawal.data.repository.cache.CachePreferencesHelperImp
import com.halim.hotelstajawal.data.repository.cache.database.HotelsDatabase
import com.halim.hotelstajawal.data.repository.cache.database.HotelsRepositoryDBCache
import com.halim.hotelstajawal.data.repository.cache.database.dao.HotelDao
import com.halim.hotelstajawal.di.qualifier.AppQualifier
import com.halim.hotelstajawal.di.scope.AppScope
import com.halim.hotelstajawal.domain.repository.HotelRepository
import com.halim.hotelstajawal.domain.repository.cache.CachePreferencesHelper
import com.halim.hotelstajawal.domain.repository.cache.HotelsRepositoryCache
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [(DataSetModule::class), (RepositoryModule.Bind::class)])
class RepositoryModule {

    //======================= Hotel Repository =======================
    @Provides
    @AppScope
    fun provideAddressMapper() = AddressMapper()

    @Provides
    @AppScope
    fun provideHotelMapper(addressMapper: AddressMapper) = HotelMapper(addressMapper)

    @Provides
    @AppScope
    fun provideHotelsDatabase(@AppQualifier context: Context): HotelsDatabase =
            HotelsDatabase.getInstance(context)

    @Provides
    @AppScope
    fun provideHotelDao(db: HotelsDatabase): HotelDao =
            db.getHotelDao()

    @Provides
    @AppScope
    fun provideHotelsRepositoryCache(hotelDao: HotelDao,
                                     prefHelper: CachePreferencesHelper): HotelsRepositoryCache =
            HotelsRepositoryDBCache(hotelDao, prefHelper)

    @Provides
    @AppScope
    fun provideHotelRepository(hotelDataSet: HotelDataSet, hotelMapper: HotelMapper,
                               cacheRepo: HotelsRepositoryCache): HotelRepository =
            HotelRepositoryImp(hotelDataSet, hotelMapper, cacheRepo)


    @Module
    abstract class Bind {

        @Binds
        @AppScope
        abstract fun bindCachePreferencesHelper(helper: CachePreferencesHelperImp): CachePreferencesHelper
    }
}