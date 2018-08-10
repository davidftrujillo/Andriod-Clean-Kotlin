package com.xmoba.data.repository

import com.nhaarman.mockito_kotlin.*
import com.xmoba.data.mapper.*
import com.xmoba.data.model.user.*
import com.xmoba.data.persistence.database.dao.UserDao
import com.xmoba.data.persistence.sharedpreferences.KeyValueStorage
import com.xmoba.data.repository.datasource.UserDataSource
import com.xmoba.data.repository.datasource.UserDataSourceFactory
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

/**
 * Created by david on 9/8/18.
 */
class UserDataRepositoryTest {

    lateinit var userDataRepository: UserDataRepository
    lateinit var userEntityMapper: UserEntityMapper
    val userDataSourceFactory: UserDataSourceFactory = mock()
    val keyValueStorage: KeyValueStorage = mock()
    val userDataSource: UserDataSource = mock()
    val userDao: UserDao = mock()

    @Before
    fun setUp() {

        Mockito.`when`(userDataSourceFactory.getLocalDataSource()).thenReturn(userDataSource)
        Mockito.`when`(userDataSourceFactory.getRemoteDataSource()).thenReturn(userDataSource)
        Mockito.`when`(userDataSource.getUsers(any(), any())).thenReturn(Observable.just(getUserList()))
        Mockito.`when`(userDataSource.getUserByEmail(any())).thenReturn(Observable.just(createUserEntity()))

        userEntityMapper = UserEntityMapper(UserPictureEntityMapper(), UserNameEntityMapper(), UserDateEntityMapper(), UserLocationEntityMapper())
        userDataRepository = UserDataRepository(userDataSourceFactory, keyValueStorage, userEntityMapper, userDao)
    }

    @Test
    fun whenGetUsersWithValidCache_ThenGetUsersFromLocal() {

        Mockito.`when`(keyValueStorage.readLong("last_request_get_users_2_10")).thenReturn(Calendar.getInstance().timeInMillis)
        val page = 2
        val pageSize = 10

        userDataRepository.getUsers(page, pageSize)

        verify(userDataSource, times(1)).getUsers(page, pageSize)
        verifyNoMoreInteractions(userDao)
    }

    @Test
    fun whenGetUserByEmail_ThenSearchInLocal() {

        val email = "email@email.com"
        userDataRepository.getUserByEmail(email)

        verify(userDataSource, times(1)).getUserByEmail(email)
    }

    private fun getUserList(): List<UserEntity> {

        val userEntity = createUserEntity()
        val userList = mutableListOf<UserEntity>()

        for (i in 1..10) {
            userList.add(userEntity)
        }

        return userList
    }

    private fun createUserEntity(): UserEntity {

        val userNameEntity = UserNameEntity("sir", "name", "lastname")
        val coordinates = CoordinatesEntity("12.123", "-6.765")
        val timezone = TimezoneEntity("-6", "description")
        val userLocationEntity = UserLocationEntity("street", "city", "state",
                "08210", coordinates, timezone)
        val userLoginEntity = UserLoginEntity("1234567890", "username", "1234",
                "salt", "md5", "sha1", "sha256")
        val dateBirthdayEntity = UserDateEntity("2011-11-04T00:38:56Z", 3)
        val dateRegisterEntity = UserDateEntity("2001-11-04T00:38:56Z", 13)
        val nameValueEntity = NameValueEntity("DNI", "45322234T")
        val pictureEntity = UserPictureEntity("http://large.jpg", "http://medium.jpg", "http://thumbnail.jpg")

        val userEntity = UserEntity("male", userNameEntity, userLocationEntity,
                "email@email.com", userLoginEntity, dateBirthdayEntity, dateRegisterEntity,
                "999888777", "666555222", nameValueEntity, pictureEntity, "ES", 5)
        return userEntity
    }
}