package com.halim.hotelstajawal.domain.presenter

import com.halim.hotelstajawal.domain.view.View
import java.lang.ref.WeakReference


abstract class Presenter<out V : View>(val view: V) {


}