package com.xmoba.data.repository

import com.xmoba.data.mapper.UserEntityMapper
import com.xmoba.data.persistence.database.dao.UserDao
import com.xmoba.data.model.user.UserEntity
import com.xmoba.data.persistence.sharedpreferences.KeyValueStorage
import com.xmoba.data.repository.datasource.UserDataSource
import com.xmoba.data.repository.datasource.UserDataSourceFactory
import com.xmoba.domain.model.User
import com.xmoba.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDataRepository @Inject constructor(
        private val factory: UserDataSourceFactory,
        private val keyValueStorage: KeyValueStorage,
        private val userEntityMapper: UserEntityMapper,
        private val userDao: UserDao) : UserRepository {

    private val FIVE_MINUTES_MILLIS = 300000L

    override fun getUsers(page: Int, pageSize: Int): Observable<List<User>> {

        val now = System.currentTimeMillis()
        val lastRequest = keyValueStorage.readLong("last_request_get_users_${page}_$pageSize")
        var needToSave: Boolean
        var dataSource: UserDataSource?

        if (Math.abs(now - lastRequest) < FIVE_MINUTES_MILLIS) {
            dataSource = factory.getLocalDataSource()
            needToSave = false
        } else {
            dataSource = factory.getRemoteDataSource()
            needToSave = true
        }

        return dataSource.getUsers(page, pageSize)
                .flatMap {
                    if (needToSave) {
                        saveUsers(it, page, pageSize).toSingleDefault(it).toObservable()
                    } else {
                        Observable.just(it)
                    }
                }
                .map { this.userEntityMapper.mapList(it) }
    }

    override fun getUserByEmail(email: String): Observable<User> {

        val dataSource = factory.getLocalDataSource()

        return dataSource.getUserByEmail(email).map { this.userEntityMapper.map(it) }
    }

    private fun saveUsers(users: List<UserEntity>, page: Int, pageSize: Int): Completable {

        var orderStart = (page * pageSize) - pageSize

        for (user in users) {
            user.order = orderStart++
            userDao.insert(user)
        }
        return Completable.complete()
    }
}