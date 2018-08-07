package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserDateEntity
import com.xmoba.domain.model.UserDate
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDateMapper @Inject constructor(): Mapper<UserDateEntity, UserDate> {

    override fun map(entityObject: UserDateEntity): UserDate {

        return UserDate(entityObject.date, entityObject.age)
    }
}