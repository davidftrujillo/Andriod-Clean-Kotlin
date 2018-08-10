package com.xmoba.domain.interactor

import com.nhaarman.mockito_kotlin.mock
import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 10/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    lateinit var useCase: UseCaseTestClass
    lateinit var testObserver: TestDisposableObserver<Any>
    val threadExecutor: ThreadExecutor = mock()
    val postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {

        useCase = UseCaseTestClass(threadExecutor, postExecutionThread)
        testObserver = TestDisposableObserver()
        Mockito.`when`(postExecutionThread.getScheduler()).thenReturn(TestScheduler())
    }

    @Test
    fun whenExecuteUseCase_ThenReturnCorrectResult() {

        useCase.execute(testObserver, "")
        assertEquals(0, testObserver.getValuesCount())
    }

    @Test
    fun whenExecuteAndDispose_ThenObserverIsDisposed() {

        useCase.execute(testObserver, "")
        useCase.dispose()

        assert(testObserver.isDisposed)
    }

    class UseCaseTestClass constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<Any, Any>(threadExecutor, postExecutionThread) {

        override fun buildUseCaseObservable(params: Any): Observable<Any> {

            return Observable.empty()
        }
    }

    class TestDisposableObserver<T> : DisposableObserver<T>() {

        private var valuesCount = 0

        override fun onNext(value: T) {
            valuesCount++
        }

        override fun onError(e: Throwable) {
            // no-op by default.
        }

        override fun onComplete() {
            // no-op by default.
        }

        fun getValuesCount() = valuesCount
    }
}