package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserPictureEntity
import com.xmoba.domain.model.UserPicture
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserPictureEntityMapper @Inject constructor(): Mapper<UserPictureEntity, UserPicture> {

    override fun map(entityObject: UserPictureEntity): UserPicture {

        return UserPicture(entityObject.large, entityObject.medium, entityObject.thumbnail)
    }
}