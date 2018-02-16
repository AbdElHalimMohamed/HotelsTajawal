package com.halim.hotelstajawal.domain.usecase.observers

import com.halim.hotelstajawal.domain.exception.DomainException
import com.halim.hotelstajawal.domain.exception.ExceptionHandler
import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.value
import com.halim.hotelstajawal.domain.view.View
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


open class RetryDisposableSubscriber<T>(private val threadExecutor: ThreadExecutor,
                                        private val uiExecutor: PostExecutionThread,
                                        baseView: WeakReference<out View>)
    : SimpleDisposableSubscriber<T>(baseView) {

    private var emitter: ObservableEmitter<Any?>? = null
    private val retryObservable = Observable.create<Any?> { emitter ->
        this@RetryDisposableSubscriber.emitter = emitter
    }

    fun makeObservableRetryable(observable: Flowable<T>): Flowable<T> =
            observable.doOnError {
                uiExecutor.scheduler.scheduleDirect({
                    onError(it)
                })
            }.retryWhen { error ->
                        error.flatMap {
                            retryObservable.subscribeOn(uiExecutor.scheduler)
                                    .observeOn(Schedulers.from(threadExecutor)).toFlowable(BackpressureStrategy.DROP)
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
        baseView.value?.showRetry(domainException, {
            onRetry()
            baseView.value?.showLoadingDataProgress()
        }, {
            onClose()
            baseView.value?.close()
        })
    }
}