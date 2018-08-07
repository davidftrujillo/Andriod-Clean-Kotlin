package com.xmoba.data.repository.datasource

import com.xmoba.data.internal.di.components.DaggerNetworkComponent
import com.xmoba.data.model.user.UserEntity
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class RemoteUserDataSource @Inject constructor(private val retrofit: Retrofit): UserDataSource  {

    override fun getUsers(): Observable<List<UserEntity>> {

        DaggerNetworkComponent.builder().build().inject(this)
        return retrofit.create(ApiService::class.java).getUsers()
    }
}