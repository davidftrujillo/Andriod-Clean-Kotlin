package com.xmoba.xmoba.model

/**
 * Created by david on 7/8/18.
 */
data class UserView(
        val uuid: String,
        val gender: String,
        val email: String,
        val phone: String,
        val cell: String,
        val nationality: String,
        val picture: UserPictureView,
        val userName: UserNameView,
        val birthday: UserDateView,
        val registered: UserDateView,
        val location: UserLocationView
)