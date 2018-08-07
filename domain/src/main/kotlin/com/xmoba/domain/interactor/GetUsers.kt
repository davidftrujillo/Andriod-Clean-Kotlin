package com.xmoba.domain.interactor

import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.domain.model.User
import com.xmoba.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class GetUsers @Inject constructor(
        private val userRepository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread): UseCaseArgumentless<List<User>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(): Observable<List<User>> {

        return userRepository.getUsers()
    }
}