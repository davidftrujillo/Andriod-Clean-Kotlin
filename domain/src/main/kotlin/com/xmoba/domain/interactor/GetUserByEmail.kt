package com.xmoba.domain.interactor

import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.domain.model.User
import com.xmoba.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 8/8/18.
 */
class GetUserByEmail @Inject constructor(
        private val userRepository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread): UseCase<User, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(email: String): Observable<User> {

        return userRepository.getUserByEmail(email)
    }
}