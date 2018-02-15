package com.halim.hotelstajawal.domain.presenter

import com.halim.hotelstajawal.domain.view.View
import java.lang.ref.WeakReference


abstract class Presenter<V : View>(view: V) {

    val view: WeakReference<V> = WeakReference(view)

    open fun dispose() {}
}