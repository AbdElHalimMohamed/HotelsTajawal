package com.halim.hotelstajawal.domain

import android.view.View
import java.lang.ref.WeakReference


val <T> WeakReference<T>.value: T?
    get() = this.get()

inline fun <reified T> View.getBoundEntity(): T? = tag as T

inline fun <reified T> View.setBoundEntity(e: T?) {
    tag = e
}