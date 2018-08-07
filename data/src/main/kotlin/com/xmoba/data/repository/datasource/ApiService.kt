package com.xmoba.data.repository.datasource

import com.xmoba.data.model.user.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by david on 7/8/18.
 */
interface ApiService {

    @GET
    fun getUsers(): Observable<List<UserEntity>>
}