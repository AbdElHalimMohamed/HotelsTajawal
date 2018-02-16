package com.halim.hotelstajawal.domain.presenter.bus

import android.support.v4.util.ArrayMap
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class RXBus @Inject constructor(): Bus {

    private val publisher = PublishSubject.create<Any>()
    private val subscription = ArrayMap<Any, CompositeDisposable>()

    override fun post(event: Any) {
        publisher.onNext(event)
    }

    override fun <T> register(lifeCycle: Any, eventType: Class<T>, action: (t:T) -> Unit) {
        getSubscriber(lifeCycle).add(publisher.ofType(eventType).subscribe(action))
    }

    override fun unregister(lifeCycle: Any) {
        subscription.remove(lifeCycle)?.dispose()
    }

    private fun getSubscriber(lifeCycle: Any): CompositeDisposable =
            subscription[lifeCycle]?.takeUnless { it.isDisposed }
                    ?: CompositeDisposable().let { subscription[lifeCycle] = it; it }
}