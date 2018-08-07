package com.xmoba.data.model.user

/**
 * Created by david on 6/8/18.
 */
data class UserEntity(
        val gender: String,
        val name: UserNameEntity,
        val location: UserLocationEntity,
        val email: String,
        val login: UserLoginEntity,
        val dob: UserDateEntity,
        val registered: UserDateEntity,
        val phone: String,
        val cell: String,
        val id: NameValueEntity,
        val picture: UserPictureEntity,
        val nat: String
)