package com.xmoba.domain.repository

import com.xmoba.domain.model.User
import io.reactivex.Observable

/**
 * Created by david on 7/8/18.
 */
interface UserRepository {

    fun getUsers(): Observable<List<User>>
}