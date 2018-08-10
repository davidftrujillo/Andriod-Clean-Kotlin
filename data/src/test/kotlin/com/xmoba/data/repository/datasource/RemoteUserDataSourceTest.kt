package com.xmoba.data.repository.datasource

import com.nhaarman.mockito_kotlin.*
import com.xmoba.data.model.response.ResponseData
import com.xmoba.data.model.response.ResponseInfo
import com.xmoba.data.model.user.*
import com.xmoba.data.persistence.sharedpreferences.KeyValueStorage
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class RemoteUserDataSourceTest {

    lateinit var remoteUserDataSource: RemoteUserDataSource
    val retrofit: Retrofit = mock()
    val apiService: ApiService = mock()
    val keyValueStorage: KeyValueStorage = mock()


    @Before
    fun setUp() {

        Mockito.`when`(retrofit.create(ApiService::class.java)).thenReturn(apiService)
        Mockito.`when`(apiService.getUsers(any(), any())).thenReturn(Observable.just(getResponseData()))
        remoteUserDataSource = RemoteUserDataSource(retrofit, keyValueStorage)
    }

    @Test
    fun whenGetUsers_ThenCallGetUsersFromApi() {

        remoteUserDataSource.getUsers(2, 10)

        verify(apiService, times(1)).getUsers(2, 10)
        verify(retrofit, times(1)).create(ApiService::class.java)
    }

    @Test
    fun whenGetUsers_ThenSaveMillisInKeyValueStorage() {

        val userObservable = remoteUserDataSource.getUsers(2, 10)

        val testObserver = TestObserver<List<UserEntity>>()
        userObservable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertValueCount(1)

        verify(keyValueStorage, times(1)).save(eq("last_request_get_users_2_10"), any())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun whenGetUserByEmail_ThenThrowUnsupportedOPerationException() {

        remoteUserDataSource.getUserByEmail("mail@mail.com")
    }

    private fun getResponseData(): ResponseData {

        val userEntity = createUserEntity()
        val userList = mutableListOf<UserEntity>()

        for (i in 1..10) {
            userList.add(userEntity)
        }

        return ResponseData(userList, ResponseInfo("xmoba", 10, 2, "1.0"))
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