package com.halim.hotelstajawal.domain.usecase

import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.usecase.observers.RetryDisposableSubscriber
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import io.reactivex.subscribers.ResourceSubscriber


abstract class UseCase<T, in Params>(private val threadExecutor: ThreadExecutor,
                                     private val uiExecutor: PostExecutionThread) {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var flowable: Flowable<T>

    abstract fun buildUseCaseObservable(params: Params): Flowable<T>

    fun execute(params: Params, subscriber: DisposableSubscriber<T>) {
        if (this::flowable.isInitialized.not()) {
            flowable = buildUseCaseObservable(params)
        }

        val finalObservable = when (subscriber) {
            is RetryDisposableSubscriber -> subscriber.makeObservableRetryable(flowable)
            else -> flowable
        }

        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        disposables.add(finalObservable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutor.scheduler)
                .subscribeWith(subscriber))
    }

    fun dispose() {
        disposables.dispose()
    }
}