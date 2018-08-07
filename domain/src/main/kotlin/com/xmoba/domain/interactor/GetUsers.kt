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
        postExecutionThread: PostExecutionThread): UseCase<List<User>, Int>(threadExecutor, postExecutionThread) {

    private val pageSize = 10

    override fun buildUseCaseObservable(page: Int): Observable<List<User>> {

        return userRepository.getUsers(page, pageSize)
    }
}