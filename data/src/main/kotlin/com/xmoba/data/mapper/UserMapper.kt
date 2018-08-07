package com.xmoba.data.mapper

import com.xmoba.data.model.user.UserEntity
import com.xmoba.domain.model.User
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserMapper @Inject constructor(private val userPictureMapper: UserPictureMapper,
                                     private val userNameMapper: UserNameMapper,
                                     private val userDateMapper: UserDateMapper,
                                     private val userLocationMapper: UserLocationMapper): Mapper<UserEntity, User> {

    override fun map(entityObject: UserEntity): User {

        return User(entityObject.login.uuid, entityObject.gender, entityObject.email, entityObject.phone,
                entityObject.cell, entityObject.nat, userPictureMapper.map(entityObject.picture),
                userNameMapper.map(entityObject.name), userDateMapper.map(entityObject.dob),
                userDateMapper.map(entityObject.registered), userLocationMapper.map(entityObject.location))
    }


}