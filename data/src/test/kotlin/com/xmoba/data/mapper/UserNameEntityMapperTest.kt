package com.xmoba.data.mapper

import com.xmoba.data.model.user.CoordinatesEntity
import com.xmoba.data.model.user.TimezoneEntity
import com.xmoba.data.model.user.UserLocationEntity
import com.xmoba.data.model.user.UserNameEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserNameEntityMapperTest {

    lateinit var userNameEntityMapper: UserNameEntityMapper

    @Before
    fun setUp() {

        userNameEntityMapper = UserNameEntityMapper()
    }

    @Test
    fun givenUserNameEntity_WhenMapToDomainObject_ThenDataIsValid() {

        val entity = UserNameEntity("Title", "First", "Last")

        val userName = userNameEntityMapper.map(entity)

        assertEquals(entity.title, userName.title)
        assertEquals(entity.first, userName.firstName)
        assertEquals(entity.last, userName.lastName)
    }
}