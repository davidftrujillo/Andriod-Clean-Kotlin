package com.xmoba.data.repository

import com.xmoba.data.mapper.UserEntityMapper
import com.xmoba.data.repository.datasource.UserDataSourceFactory
import com.xmoba.domain.model.User
import com.xmoba.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDataRepository @Inject constructor(
        private val factory: UserDataSourceFactory,
        private val userEntityMapper: UserEntityMapper): UserRepository {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<User>> {

        val dataSource = factory.getDataSource()

        return dataSource.getUsers(page, pageSize)
                .map { this.userEntityMapper.mapList(it) }
    }
}