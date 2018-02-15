package com.halim.hotelstajawal.domain.usecase

import com.halim.hotelstajawal.domain.excutor.PostExecutionThread
import com.halim.hotelstajawal.domain.excutor.ThreadExecutor
import com.halim.hotelstajawal.domain.usecase.observers.RetryDisposableObserver
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCase<T, in Params>(private val threadExecutor: ThreadExecutor,
                                     private val uiExecutor: PostExecutionThread) {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var observable: Observable<T>

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(params: Params, observer: DisposableObserver<T>) {
        if (this::observable.isInitialized.not()) {
            observable = buildUseCaseObservable(params)
        }

        val finalObservable = when (observer) {
            is RetryDisposableObserver -> observer.makeObservableRetryable(observable)
            else -> observable
        }

        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        disposables.add(finalObservable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutor.scheduler)
                .subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }
}