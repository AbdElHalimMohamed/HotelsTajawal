package com.halim.hotelstajawal.domain.presenter

import android.support.v4.util.ArrayMap
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.view.View
import java.lang.ref.WeakReference


abstract class Presenter<V : View>(val bus: Bus, view: V) {

    val view: WeakReference<V> = WeakReference(view)

    private val busParams: ArrayMap<Class<*>, Any> = ArrayMap()

    init {
        this.registerEvents(bus)
    }

    fun <T> registerReceivingEvent(eventType: Class<T>, action: (t: T) -> Unit) {
        busParams[eventType] = action
    }

    open fun pauseReceivingEvents() {
        pauseEventsExcept()
    }

    open fun resumeReceivingEvents() {
        bus.unregister(this)
        for ((event, action) in busParams) {
            @Suppress("UNCHECKED_CAST")
            bus.register(this, event, action as (t: Any?) -> Unit)
        }
    }

    protected fun pauseEventsExcept(vararg except: Class<*>) {
        bus.unregister(this)
        if (except.isNotEmpty()) {
            for (eventType in except) {
                @Suppress("UNCHECKED_CAST")
                bus.register(this, eventType, busParams[eventType] as (t: Any?) -> Unit)
            }
        }
    }

    protected abstract fun registerEvents(bus: Bus)

    open fun dispose() {}
}