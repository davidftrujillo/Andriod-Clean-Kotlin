package com.xmoba.data.repository.datasource

import com.xmoba.data.model.user.UserEntity
import io.reactivex.Observable

/**
 * Created by david on 7/8/18.
 */
interface UserDataSource {

    fun getUsers(page: Int, pageSize: Int): Observable<List<UserEntity>>
}