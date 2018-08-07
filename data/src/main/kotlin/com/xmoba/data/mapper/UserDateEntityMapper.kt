package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserDateEntity
import com.xmoba.domain.model.UserDate
import java.text.SimpleDateFormat
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDateEntityMapper @Inject constructor(): Mapper<UserDateEntity, UserDate> {

    override fun map(entityObject: UserDateEntity): UserDate {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateMillis = dateFormat.parse(entityObject.date).time

        return UserDate(dateMillis, entityObject.age)
    }
}