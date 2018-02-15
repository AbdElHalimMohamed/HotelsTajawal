package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.observers.DisposableObserver


open class SimpleDisposableObserver<T>(val baseView: View) : DisposableObserver<T>() {

    override fun onStart() {
        baseView.showLoadingDataProgress()
    }

    override fun onComplete() {
        baseView.hideLoadingDataProgress()
    }

    override fun onNext(data: T) {

    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        baseView.showErrorMsg(exception)
    }
}