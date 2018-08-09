package com.xmoba.data.repository.datasource

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserDataSourceFactoryTest {

    lateinit var userDataStoreFactory: UserDataSourceFactory
    val remoteUserDataSource : RemoteUserDataSource = mock()
    val localUserDataSource : LocalUserDataSource = mock()

    @Before
    fun setUp() {

        userDataStoreFactory = UserDataSourceFactory(remoteUserDataSource, localUserDataSource)
    }

    @Test
    fun whenGetDataSource_ThenReturnAlwaysRemoteDataStore() {

        assertEquals(remoteUserDataSource, userDataStoreFactory.getDataSource())
        assert(userDataStoreFactory.getDataSource() is RemoteUserDataSource)
    }

    @Test
    fun whenGetRemoteDataSource_ThenReturnAlwaysRemoteDataStore() {

        assertEquals(remoteUserDataSource, userDataStoreFactory.getRemoteDataSource())
    }

    @Test
    fun whenGetLocalDataSource_ThenReturnAlwaysLocalDataStore() {

        assertEquals(localUserDataSource, userDataStoreFactory.getLocalDataSource())
    }
}