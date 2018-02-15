package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.DomainException
import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


open class RetryDisposableObserver<T>(baseView: WeakReference<out View>)
    : SimpleDisposableObserver<T>(baseView) {

    private var emitter: ObservableEmitter<Any?>? = null
    private val retryObservable = Observable.create<Any?> { emitter ->
        this@RetryDisposableObserver.emitter = emitter
    }

    fun makeObservableRetryable(observable: Observable<T>): Observable<T> =
            observable.doOnError { onError(it) }
                    .retryWhen { error ->
                        error.flatMap {
                            retryObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                        }
                    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.getException(e)
        if (exception.retry) {
            onError(exception, { emitter?.onNext(0) }, { emitter?.onComplete() })
        } else {
            baseView.value?.showErrorMsg(exception)
        }
        baseView.value?.hideLoadingDataProgress()
        e.printStackTrace()
    }

    open fun onError(domainException: DomainException, onRetry: () -> Unit, onClose: () -> Unit) {
        baseView.value?.showRetry(domainException, onRetry, {
            onClose()
            baseView.value?.close()
        })
    }
}