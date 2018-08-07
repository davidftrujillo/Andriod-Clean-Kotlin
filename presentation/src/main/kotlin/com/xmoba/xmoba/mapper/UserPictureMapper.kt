package com.xmoba.xmoba.mapper

import com.xmoba.domain.model.UserPicture
import com.xmoba.xmoba.model.UserPictureView
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserPictureMapper @Inject constructor(): Mapper<UserPicture, UserPictureView> {

    override fun map(domainObject: UserPicture): UserPictureView {

        return UserPictureView(domainObject.large, domainObject.medium, domainObject.thumbnail)
    }
}