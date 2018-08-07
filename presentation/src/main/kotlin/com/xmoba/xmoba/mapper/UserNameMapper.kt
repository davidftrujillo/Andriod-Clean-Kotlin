package com.xmoba.xmoba.mapper

import com.xmoba.domain.model.UserName
import com.xmoba.xmoba.model.UserNameView
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserNameMapper @Inject constructor(): Mapper<UserName, UserNameView> {

    override fun map(domainObject: UserName): UserNameView {

        return UserNameView(domainObject.title, domainObject.firstName, domainObject.lastName)
    }
}