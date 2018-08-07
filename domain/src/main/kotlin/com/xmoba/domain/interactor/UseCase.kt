package com.xmoba.domain.interactor

import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by david on 7/8/18.
 */
abstract class UseCase<T, in Params>(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}
     */
    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    /**
     * Executes the current use case
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable built
     * by {@link #buildUseCaseObservable(Params)} ()} method
     * @param params Parameters (optional) used to build/execute this use case
     */
    fun execute(observer: DisposableObserver<T>, params: Params) {

        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current {@link CompositeDisposable}
     */
    fun dispose() {

        if (!disposables.isDisposed) {

            disposables.dispose()
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}
     */
    private fun addDisposable(disposable: Disposable) {

        disposables?.add(disposable)
    }
}