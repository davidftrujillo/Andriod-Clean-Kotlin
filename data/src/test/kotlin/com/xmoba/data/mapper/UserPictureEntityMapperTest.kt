package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserPictureEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserPictureEntityMapperTest {

    lateinit var userPictureEntityMapper: UserPictureEntityMapper

    @Before
    fun setUp() {

        userPictureEntityMapper = UserPictureEntityMapper()
    }

    @Test
    fun givenPictureEntity_WhenMapToDomainObject_ThenDataIsValid() {

        val entity = UserPictureEntity("http://large.jpg", "http://medium.jpg", "http://thumbnail.jpg")

        val picture = userPictureEntityMapper.map(entity)

        assertEquals(entity.large, picture.large)
        assertEquals(entity.medium, picture.medium)
        assertEquals(entity.thumbnail, picture.thumbnail)
    }
}