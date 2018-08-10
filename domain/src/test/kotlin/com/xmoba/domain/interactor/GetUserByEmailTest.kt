package com.xmoba.domain.interactor

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.domain.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by david on 10/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetUserByEmailTest {

    lateinit var getUserByEmail: GetUserByEmail

    val userRepository: UserRepository = mock()
    val threadExecutor: ThreadExecutor = mock()
    val postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {

        getUserByEmail = GetUserByEmail(userRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun whenExecuteUseCase_ThenGetUsersFromRepositoryOnce() {

        getUserByEmail.buildUseCaseObservable("email@email.com")

        verify(userRepository, times(1)).getUserByEmail("email@email.com")
        verifyZeroInteractions(threadExecutor)
        verifyZeroInteractions(postExecutionThread)
    }
}