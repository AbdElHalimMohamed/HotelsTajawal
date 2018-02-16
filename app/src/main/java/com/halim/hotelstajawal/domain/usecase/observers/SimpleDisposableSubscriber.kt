package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.subscribers.DisposableSubscriber
import java.lang.ref.WeakReference


open class SimpleDisposableSubscriber<T>(val baseView: WeakReference<out View>) :
        DisposableSubscriber<T>() {

    override fun onStart() {
        super.onStart()
        baseView.value?.showLoadingDataProgress()
    }

    override fun onComplete() {
    }

    override fun onNext(data: T) {
        baseView.value?.hideLoadingDataProgress()
    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        baseView.value?.showErrorMsg(exception)
    }
}