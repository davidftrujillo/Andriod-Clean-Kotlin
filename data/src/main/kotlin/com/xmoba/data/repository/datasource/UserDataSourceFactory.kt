package com.xmoba.data.repository.datasource

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by david on 7/8/18.
 */
@Singleton
class UserDataSourceFactory @Inject constructor(
        private val remoteUserDataSource: RemoteUserDataSource,
        private val localUserDataSource: LocalUserDataSource){

    fun getDataSource(): UserDataSource {

        // TODO for now, return always the remote one. We can implement some policy to know when use remote or local data source
        return getRemoteDataSource()
    }

    fun getRemoteDataSource(): UserDataSource {

        return remoteUserDataSource
    }

    fun getLocalDataSource(): UserDataSource {

        return localUserDataSource
    }
}