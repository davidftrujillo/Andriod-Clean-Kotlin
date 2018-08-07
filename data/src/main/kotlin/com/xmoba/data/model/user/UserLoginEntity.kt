package com.xmoba.data.model.user

/**
 * Created by david on 6/8/18.
 */
class UserLoginEntity(
        val uuid: String,
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
)