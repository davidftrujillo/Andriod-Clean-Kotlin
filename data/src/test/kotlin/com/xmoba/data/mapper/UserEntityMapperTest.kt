package com.xmoba.data.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.xmoba.data.model.user.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserEntityMapperTest {

    lateinit var userEntityMapper: UserEntityMapper

    val mockUserPictureMapper: UserPictureEntityMapper = mock()
    val mockUserNameEntityMapper: UserNameEntityMapper = mock()
    val mockUserLocationEntityMapper: UserLocationEntityMapper = mock()
    val mockUserDateEntityMapper: UserDateEntityMapper = mock()

    @Before
    fun setUp() {

        Mockito.`when`(mockUserPictureMapper.map(any())).thenCallRealMethod()
        Mockito.`when`(mockUserNameEntityMapper.map(any())).thenCallRealMethod()
        Mockito.`when`(mockUserLocationEntityMapper.map(any())).thenCallRealMethod()
        Mockito.`when`(mockUserDateEntityMapper.map(any())).thenCallRealMethod()
        userEntityMapper = UserEntityMapper(mockUserPictureMapper, mockUserNameEntityMapper,
                mockUserDateEntityMapper, mockUserLocationEntityMapper)
    }

    @Test
    fun givenUserEntity_WhenMapToDomainObject_ThenInnerObjectsMappersAreCalled() {

        val userEntity = createUserEntity()

        val user = userEntityMapper.map(userEntity)

        verify(mockUserPictureMapper, times(1)).map(userEntity.picture)
        verify(mockUserNameEntityMapper, times(1)).map(userEntity.name)
        verify(mockUserLocationEntityMapper, times(1)).map(userEntity.location)
        verify(mockUserDateEntityMapper, times(1)).map(userEntity.dob)
        verify(mockUserDateEntityMapper, times(1)).map(userEntity.registered)
    }

    @Test
    fun givenUserEntity_WhenMapToDomainObject_ThenDataIsValid() {

        val userEntity = createUserEntity()

        val user = userEntityMapper.map(userEntity)

        assertEquals(userEntity.email, user.email)
        assertEquals(userEntity.cell, user.cell)
        assertEquals(userEntity.phone, user.phone)
        assertEquals(userEntity.gender, user.gender)
        assertEquals(userEntity.login.uuid, user.uuid)
        assertEquals(userEntity.nat, user.nationality)
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