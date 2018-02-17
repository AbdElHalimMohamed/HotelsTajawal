package com.halim.hotelstajawal.di.data.repository

import android.arch.persistence.room.Room
import com.halim.hotelstajawal.data.dataset.HotelDataSet
import com.halim.hotelstajawal.data.mapper.AddressMapper
import com.halim.hotelstajawal.data.mapper.HotelMapper
import com.halim.hotelstajawal.data.repository.HotelRepositoryImp
import com.halim.hotelstajawal.data.repository.cache.database.HotelsDatabase
import com.halim.hotelstajawal.data.repository.cache.database.HotelsRepositoryDBCache
import com.halim.hotelstajawal.data.repository.cache.database.dao.HotelDao
import com.halim.hotelstajawal.di.scope.PerTestClass
import com.halim.hotelstajawal.domain.repository.cache.CachePreferencesHelper
import com.halim.hotelstajawal.domain.repository.cache.HotelsRepositoryCache
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import org.robolectric.RuntimeEnvironment


@Module
class RepositoryModule {

    @Provides
    fun provideHotelsDatabase(): HotelsDatabase =
            Room.inMemoryDatabaseBuilder(RuntimeEnvironment.systemContext,
                    HotelsDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()

    @Provides
    fun provideHotelDao(db: HotelsDatabase): HotelDao = db.getHotelDao()

    @Provides
    @PerTestClass
    fun providePrefHelper(): CachePreferencesHelper = mock(CachePreferencesHelper::class.java)

    @Provides
    @PerTestClass
    fun provideHotelsRepositoryDBCache(hotelDoa: HotelDao, prefHelper: CachePreferencesHelper) =
            HotelsRepositoryDBCache(hotelDoa, prefHelper)

    @Provides
    @PerTestClass
    fun porvideHotelDataSet(): HotelDataSet = mock(HotelDataSet::class.java)

    @Provides
    @PerTestClass
    fun porvideHotelsRepositoryCache(): HotelsRepositoryCache =
            mock(HotelsRepositoryCache::class.java)

    @Provides
    @PerTestClass
    fun porvideAddressMapper(): AddressMapper = AddressMapper()

    @Provides
    @PerTestClass
    fun porvideHotelMapper(address: AddressMapper): HotelMapper = HotelMapper(address)

    @Provides
    @PerTestClass
    fun porvideHotelRepository(dataSet: HotelDataSet,
                               mapper: HotelMapper,
                               cache: HotelsRepositoryCache) =
            HotelRepositoryImp(dataSet, mapper, cache)
}
