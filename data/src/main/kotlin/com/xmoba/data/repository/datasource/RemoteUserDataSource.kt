package com.xmoba.data.repository.datasource

import com.xmoba.data.internal.di.components.DaggerNetworkComponent
import com.xmoba.data.model.user.UserEntity
import com.xmoba.data.persistence.sharedpreferences.KeyValueStorage
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class RemoteUserDataSource @Inject constructor(
        private val retrofit: Retrofit,
        private val keyValueStorage: KeyValueStorage) : UserDataSource {

    override fun getUsers(page: Int, pageSize: Int): Observable<List<UserEntity>> {

        DaggerNetworkComponent.builder().build().inject(this)
        return retrofit.create(ApiService::class.java)
                .getUsers(page, pageSize)
                .doOnComplete { keyValueStorage.save("last_request_get_users_${page}_$pageSize", System.currentTimeMillis()) }
                .map { it -> it.results }
    }

    override fun getUserByEmail(email: String): Observable<UserEntity> {

        throw UnsupportedOperationException("Operation not allowed. Use persistence layer to get user detail")
    }
}