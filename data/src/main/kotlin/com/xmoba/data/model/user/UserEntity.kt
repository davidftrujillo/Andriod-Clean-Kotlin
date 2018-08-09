package com.xmoba.data.model.user

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by david on 6/8/18.
 */
@Entity(tableName = "user")
data class UserEntity(
        val gender: String,
        @Embedded
        val name: UserNameEntity,
        @Embedded
        val location: UserLocationEntity,
        @PrimaryKey
        val email: String,
        @Embedded
        val login: UserLoginEntity,
        @Embedded(prefix = "dob_")
        val dob: UserDateEntity,
        @Embedded(prefix = "reg_")
        val registered: UserDateEntity,
        val phone: String,
        val cell: String,
        @Embedded
        val id: NameValueEntity,
        @Embedded
        val picture: UserPictureEntity,
        val nat: String
)