package com.xmoba.data.mapper

import com.xmoba.data.model.user.CoordinatesEntity
import com.xmoba.data.model.user.TimezoneEntity
import com.xmoba.data.model.user.UserLocationEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserLocationEntityMapperTest {

    lateinit var userLocationEntityMapper: UserLocationEntityMapper

    @Before
    fun setUp() {

        userLocationEntityMapper = UserLocationEntityMapper()
    }

    @Test
    fun givenLocationEntity_WhenMapToDomainObject_ThenDataIsValid() {

        val coordinates = CoordinatesEntity("12.123", "-6.765")
        val timezone = TimezoneEntity("-6", "description")
        val entity = UserLocationEntity("Street", "City", "State", "08210", coordinates, timezone)

        val location = userLocationEntityMapper.map(entity)

        assertEquals(entity.street, location.street)
        assertEquals(entity.city, location.city)
        assertEquals(entity.state, location.state)
        assertEquals(entity.postCode, location.postCode)
        assertEquals(12.123, location.latitude)
        assertEquals(-6.765, location.longitude)
    }
}