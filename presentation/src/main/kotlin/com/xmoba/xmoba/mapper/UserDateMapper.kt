package com.xmoba.xmoba.mapper

import com.xmoba.domain.model.UserDate
import com.xmoba.xmoba.model.UserDateView
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserDateMapper @Inject constructor(): Mapper<UserDate, UserDateView> {

    override fun map(domainObject: UserDate): UserDateView {

        return UserDateView(domainObject.date, domainObject.age)
    }
}