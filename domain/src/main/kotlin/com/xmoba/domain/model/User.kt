package com.xmoba.domain.model

/**
 * Created by david on 6/8/18.
 */
data class User(
        val uuid: String,
        val gender: String,
        val email: String,
        val phone: String,
        val cell: String,
        val nationality: String,
        val picture: UserPicture,
        val userName: UserName,
        val birthday: UserDate,
        val registered: UserDate,
        val location: UserLocation
)