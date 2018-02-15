package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference


open class SimpleDisposableObserver<T>(val baseView: WeakReference<out View>) : DisposableObserver<T>() {

    override fun onStart() {
        baseView.value?.showLoadingDataProgress()
    }

    override fun onComplete() {
        baseView.value?.hideLoadingDataProgress()
    }

    override fun onNext(data: T) {

    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        baseView.value?.showErrorMsg(exception)
    }
}