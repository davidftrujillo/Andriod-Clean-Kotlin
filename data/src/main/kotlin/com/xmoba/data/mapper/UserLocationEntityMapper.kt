package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserLocationEntity
import com.xmoba.domain.model.UserLocation
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserLocationEntityMapper @Inject constructor(): Mapper<UserLocationEntity, UserLocation> {

    override fun map(entityObject: UserLocationEntity): UserLocation {

        val latitude = entityObject.coordinates?.latitude?.toDouble()
        val longitude = entityObject.coordinates?.longitude?.toDouble()
        val postCode = entityObject.postCode as? String ?: entityObject.postCode.toString()

        return UserLocation(entityObject.street, entityObject.city, entityObject.state,
                postCode, latitude, longitude)
    }
}