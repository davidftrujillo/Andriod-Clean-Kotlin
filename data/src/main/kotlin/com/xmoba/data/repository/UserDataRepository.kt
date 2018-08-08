package com.xmoba.data.repository

import com.xmoba.data.mapper.UserEntityMapper
import com.xmoba.data.model.database.dao.UserDao
import com.xmoba.data.model.user.UserEntity
import com.xmoba.data.repository.datasource.UserDataSourceFactory
import com.xmoba.domain.model.User
import com.xmoba.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDataRepository @Inject constructor(
        private val factory: UserDataSourceFactory,
        private val userEntityMapper: UserEntityMapper,
        private val userDao: UserDao): UserRepository {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<User>> {

        val dataSource = factory.getDataSource()

        return dataSource.getUsers(page, pageSize)
                .flatMap {
                    saveUsers(it).toSingleDefault(it).toObservable()
                }
                .map { this.userEntityMapper.mapList(it) }
    }

    private fun saveUsers(users: List<UserEntity>): Completable {

        for (user in users) {
            userDao.insert(user)
        }
        return Completable.complete()
    }
}