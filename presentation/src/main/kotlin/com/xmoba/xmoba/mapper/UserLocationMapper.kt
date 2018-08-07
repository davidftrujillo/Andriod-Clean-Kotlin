package com.xmoba.xmoba.mapper

import com.xmoba.domain.model.UserLocation
import com.xmoba.xmoba.model.UserLocationView
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserLocationMapper @Inject constructor(): Mapper<UserLocation, UserLocationView> {

    override fun map(domainObject: UserLocation): UserLocationView {

        return UserLocationView(domainObject.street, domainObject.city, domainObject.state,
                domainObject.postCode, domainObject.latitude, domainObject.longitude)
    }
}