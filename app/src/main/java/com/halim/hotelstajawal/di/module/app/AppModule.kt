package com.halim.hotelstajawal.di.module.app

import android.app.Application
import android.content.Context
import com.halim.hotelstajawal.App
import com.halim.hotelstajawal.di.qualifier.AppQualifier
import com.halim.hotelstajawal.di.scope.AppScope
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.excutor.ThreadPoolExecutor
import com.halim.hotelstajawal.domain.excutor.UIExecutor
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [(AndroidInjectionModule::class), (AppModule.Bind::class)])
class AppModule {


    @Module
    abstract class Bind {

        @Binds
        @AppScope
        abstract fun bindApp(app: App): Application

        @Binds
        @AppScope
        @AppQualifier
        abstract fun bindAppContext(context: App): Context

        @Binds
        @AppScope
        abstract fun bindThreadExecutor(threadPoolExecutor: ThreadPoolExecutor): ThreadExecutor

        @Binds
        @AppScope
        abstract fun bindUIExecutor(uiExecutor: UIExecutor): PostExecutionThread
    }
}