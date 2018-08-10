package com.xmoba.data.repository.datasource

import com.xmoba.data.model.user.UserEntity
import com.xmoba.data.persistence.database.dao.UserDao
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class LocalUserDataSource @Inject constructor(private val userDao: UserDao): UserDataSource {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<UserEntity>> {

        return Observable.fromCallable ({
            userDao.getUsers((page-1)*pageSize, pageSize)
        })
    }

    override fun getUserByEmail(email: String): Observable<UserEntity> {

        return Observable.fromCallable({
            userDao.getUserByEmail(email)
        })
    }
}