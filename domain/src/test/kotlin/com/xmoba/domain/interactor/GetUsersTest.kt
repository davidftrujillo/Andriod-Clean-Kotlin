package com.xmoba.domain.interactor

import com.nhaarman.mockito_kotlin.*
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
class GetUsersTest {

    lateinit var getUsers: GetUsers

    val userRepository: UserRepository = mock()
    val threadExecutor: ThreadExecutor = mock()
    val postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {

        getUsers = GetUsers(userRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun whenExecuteUseCase_ThenGetUsersFromRepositoryOnce() {

        getUsers.buildUseCaseObservable(2)

        verify(userRepository, times(1)).getUsers(2, 10)
        verifyZeroInteractions(threadExecutor)
        verifyZeroInteractions(postExecutionThread)
    }
}