package com.xmoba.xmoba.mapper

import com.xmoba.domain.model.User
import com.xmoba.xmoba.model.UserView
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserMapper @Inject constructor(private val userDateMapper: UserDateMapper,
                                     private val userNameMapper: UserNameMapper,
                                     private val userLocationMapper: UserLocationMapper,
                                     private val userPictureMapper: UserPictureMapper): Mapper<User, UserView> {

    override fun map(domainObject: User): UserView {

        return UserView(domainObject.uuid, domainObject.gender, domainObject.email, domainObject.phone,
                domainObject.cell, domainObject.nationality, userPictureMapper.map(domainObject.picture),
                userNameMapper.map(domainObject.userName), userDateMapper.map(domainObject.birthday),
                userDateMapper.map(domainObject.registered), userLocationMapper.map(domainObject.location))
    }
}