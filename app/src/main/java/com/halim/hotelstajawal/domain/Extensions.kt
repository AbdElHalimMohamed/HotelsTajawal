package com.halim.hotelstajawal.domain

import java.lang.ref.WeakReference


val <T> WeakReference<T>.value: T?
    get() = this.get()