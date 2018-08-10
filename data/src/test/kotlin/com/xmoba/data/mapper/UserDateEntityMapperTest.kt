package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserDateEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.exp
import kotlin.test.assertEquals

/**
 * Created by david on 9/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserDateEntityMapperTest {

    lateinit var userDateEntityMapper: UserDateEntityMapper

    @Before
    fun setUp() {

        userDateEntityMapper = UserDateEntityMapper()
    }

    @Test
    fun givenUserDateEntity_WhenMapToDomainObject_ThenAgeDoesNotChangeAndDateInMillis() {

        val dateEntity = UserDateEntity("2011-11-04T00:38:56Z", 3)
        val date = userDateEntityMapper.map(dateEntity)
        val expectedMillis = 1320363536000L

        assertEquals(expectedMillis, date.date)
        assertEquals(dateEntity.age, date.age)
    }
}