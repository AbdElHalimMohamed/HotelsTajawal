package com.halim.hotelstajawal.di.component.app

import com.halim.hotelstajawal.App
import com.halim.hotelstajawal.di.module.app.ActivitiesModule
import com.halim.hotelstajawal.di.module.app.AppModule
import com.halim.hotelstajawal.di.module.app.RepositoryModule
import com.halim.hotelstajawal.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector


@AppScope
@Component(modules = [(AppModule::class), (ActivitiesModule::class), (RepositoryModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}