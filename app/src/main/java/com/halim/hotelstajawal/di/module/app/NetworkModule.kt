package com.halim.hotelstajawal.di.module.app

import android.content.Context
import com.halim.hotelstajawal.BuildConfig
import com.halim.hotelstajawal.data.Constant
import com.halim.hotelstajawal.di.qualifier.AppQualifier
import com.halim.hotelstajawal.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideCache(@AppQualifier context: Context) = Cache(File(context.cacheDir, "retrofit"), Constant.Network.NetWorkCacheSize)

    // Add more interceptors as params
    @Provides
    @AppScope
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()

    // If there are more than one BaseUrl, add Qualifier for every Provides method
    // TODO get base url from properties
    @Provides
    @AppScope
    fun provideRestAdapter(client: OkHttpClient): Retrofit = provideRestAdapter(client, "http://gchbib.de")

    private fun provideRestAdapter(client: OkHttpClient, baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}