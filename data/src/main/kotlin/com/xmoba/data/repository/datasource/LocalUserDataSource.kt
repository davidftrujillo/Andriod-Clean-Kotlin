package com.xmoba.data.repository.datasource

import com.xmoba.data.model.database.dao.UserDao
import com.xmoba.data.model.user.UserEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class LocalUserDataSource @Inject constructor(private val userDao: UserDao): UserDataSource {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<UserEntity>> {

        throw UnsupportedOperationException("Operation not allowed. Use api requests to get user list")
    }

    override fun getUserByEmail(email: String): Observable<UserEntity> {

        return Observable.fromCallable({
            userDao.getUserByEmail(email)
        })
    }
}