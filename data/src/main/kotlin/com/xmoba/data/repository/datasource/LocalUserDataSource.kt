package com.xmoba.data.repository.datasource

import com.xmoba.data.model.user.UserEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class LocalUserDataSource @Inject constructor(): UserDataSource {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<UserEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}