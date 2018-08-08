package com.xmoba.data.model.user

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.xmoba.data.model.database.converters.CoordinatesConverter
import com.xmoba.data.model.database.converters.PostCodeConverter

/**
 * Created by david on 6/8/18.
 */
@Entity(tableName = "user")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        val _id: Int,
        val gender: String,
        @Embedded
        val name: UserNameEntity,
        @Embedded
        val location: UserLocationEntity,
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