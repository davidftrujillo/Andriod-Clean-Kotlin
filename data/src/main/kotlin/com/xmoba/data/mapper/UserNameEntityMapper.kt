package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserNameEntity
import com.xmoba.domain.model.UserName
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserNameEntityMapper @Inject constructor(): Mapper<UserNameEntity, UserName> {

    override fun map(entityObject: UserNameEntity): UserName {

        return UserName(entityObject.title, entityObject.first, entityObject.last)
    }
}