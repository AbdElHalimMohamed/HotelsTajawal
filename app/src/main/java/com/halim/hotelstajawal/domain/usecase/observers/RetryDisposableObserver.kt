package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.DomainException
import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers


open class RetryDisposableObserver<T>(baseView: View) : SimpleDisposableObserver<T>(baseView) {

    var emitter: ObservableEmitter<Any?>? = null
    private val retryObservable = Observable.create<Any?> { emitter -> this@RetryDisposableObserver.emitter = emitter }

    fun makeObservableRetryable(observable: Observable<T>): Observable<T> =
            observable.doOnError { onError(it) }
                    .retryWhen { error -> error.flatMap { retryObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()) } }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        if (exception.retry) {
            onError(exception, { emitter?.onNext(0) }, { emitter?.onComplete() })
        } else {
            baseView.showErrorMsg(exception)
        }
        baseView.hideLoadingDataProgress()
        e.printStackTrace()
    }

    open fun onError(domainException: DomainException, onRetry: () -> Unit, onClose: () -> Unit) {
        baseView.showRetry(domainException, onRetry, {
            onClose()
            baseView.close()
        })
    }
}